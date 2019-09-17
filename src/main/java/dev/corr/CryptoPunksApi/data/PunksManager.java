package dev.corr.CryptoPunksApi.data;

import com.google.common.util.concurrent.RateLimiter;
import dev.corr.CryptoPunksApi.core.CryptoPunksMarket;
import dev.corr.CryptoPunksApi.core.Punk;
import dev.corr.CryptoPunksApi.core.PunkAttributes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.tuples.generated.Tuple5;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PunksManager {
  private static final Logger LOG = LoggerFactory.getLogger(PunksManager.class);
  private static final int TOTAL_PUNKS = 10000;
  private final CryptoPunksMarket cryptoPunksMarket;
  private final Map<Integer, PunkAttributes> punkAttributesMap;
  private final Tuple5<Boolean, BigInteger, String, BigInteger, String> DEFAULT_PUNK = new Tuple5<>(false, BigInteger.ZERO, "", BigInteger.ZERO, "");
  private final RateLimiter rateLimiter;

  public PunksManager(Map<Integer, PunkAttributes> punkAttributesMap,
                      CryptoPunksMarket cryptoPunksMarket,
                      RateLimiter rateLimiter) {
    this.cryptoPunksMarket = cryptoPunksMarket;
    this.punkAttributesMap = punkAttributesMap;
    this.rateLimiter = rateLimiter;
  }

  public List<Punk> getAllForSalePunks() {
    List<CompletableFuture<Tuple5<Boolean, BigInteger, String, BigInteger, String>>> resultList = new ArrayList<>();

    IntStream listOfPunkIds = IntStream.range(0, TOTAL_PUNKS);
    List<RemoteFunctionCall> requestList;

    requestList = listOfPunkIds
        .mapToObj(BigInteger::valueOf)
        .map(cryptoPunksMarket::punksOfferedForSale)
        .collect(Collectors.toList());

    requestList.forEach(
        rfc -> {
          rateLimiter.acquire();
          resultList.add(rfc.sendAsync());
        });


    List<Punk> punks = resultList.stream()
        .map((cf) -> cf.handle((result, exception) ->
        {
          if (exception != null) {
            LOG.error("Exception requesting punk from ethereum network: ", exception);
          }
          return result != null ? result : DEFAULT_PUNK;
        }).join())
        .map(Punk::new)
        .filter(Punk::isForSale)
        .collect(Collectors.toList());

    return punks;
  }

  public Punk getIndividualPunk(BigInteger punkId) {
    CompletableFuture<Tuple5<Boolean, BigInteger, String, BigInteger, String>> punkFuture = cryptoPunksMarket.punksOfferedForSale(punkId).sendAsync();

    Optional<PunkAttributes> punkAttributes = Optional.ofNullable(punkAttributesMap.get(punkId.intValue()));
    Punk resultPunk = new Punk(punkFuture.join());
    if (!resultPunk.isForSale()) {
      resultPunk.setIndex(punkId);
    }
    return resultPunk.setPunkAttributes(punkAttributes);
  }
}
