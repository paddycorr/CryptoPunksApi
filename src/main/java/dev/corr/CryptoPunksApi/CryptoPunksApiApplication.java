package dev.corr.CryptoPunksApi;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.util.concurrent.RateLimiter;
import dev.corr.CryptoPunksApi.core.CryptoPunksMarket;
import dev.corr.CryptoPunksApi.core.PunkAttributes;
import dev.corr.CryptoPunksApi.data.PunksManager;
import dev.corr.CryptoPunksApi.resources.CryptoPunks;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class CryptoPunksApiApplication extends Application<CryptoPunksApiConfiguration> {
  private static final String ATRIBUTES_FILE = "attributes.json";
  private static final BigInteger GAS_LIMIT = BigInteger.valueOf(1);
  private static final BigInteger GAS_PRICE = BigInteger.valueOf(0);

  private final TypeReference<HashMap<Integer, PunkAttributes>> typeRef
      = new TypeReference<>() {
  };

  public static void main(final String[] args) throws Exception {
    new CryptoPunksApiApplication().run(args);
  }

  @Override
  public void initialize(final Bootstrap<CryptoPunksApiConfiguration> bootstrap) {
  }

  @Override
  public void run(final CryptoPunksApiConfiguration configuration,
                  final Environment environment) {
    Map<Integer, PunkAttributes> punkAttributesMap = getAttributesMap(environment);
    RateLimiter rateLimiter = RateLimiter.create(200);
    PunksManager punksManager = new PunksManager(punkAttributesMap, getCryptoPunksContract(configuration), rateLimiter);

    CryptoPunks cryptoPunksResource = new CryptoPunks(punksManager);
    environment.jersey().register(cryptoPunksResource);
  }

  private Map<Integer, PunkAttributes> getAttributesMap(Environment environment) {
    File file = new File(
        getClass().getClassLoader().getResource(ATRIBUTES_FILE).getFile()
    );

    try {
      return environment.getObjectMapper().readValue(file, typeRef);
    } catch (IOException ex) {
      System.out.println("ERROR: Loading punk attributes failed.");
      return new HashMap<>();
    }
  }

  private CryptoPunksMarket getCryptoPunksContract(CryptoPunksApiConfiguration configuration) {
    Web3j web3 = Web3j.build(new HttpService(configuration.getEthereumEndpoint()));
    Credentials credentials = Credentials.create(configuration.getPrivateKey());
    return CryptoPunksMarket.load(configuration.getContractAddress(), web3, credentials, GAS_LIMIT, GAS_PRICE);
  }
}
