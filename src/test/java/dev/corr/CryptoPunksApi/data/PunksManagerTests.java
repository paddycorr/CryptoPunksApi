package dev.corr.CryptoPunksApi.data;

import com.google.common.util.concurrent.RateLimiter;
import dev.corr.CryptoPunksApi.core.CryptoPunksMarket;
import dev.corr.CryptoPunksApi.core.Punk;
import dev.corr.CryptoPunksApi.core.PunkAttributes;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.web3j.abi.datatypes.Function;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.tuples.generated.Tuple5;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PunksManagerTests {
  private final PunkAttributes punkAttributes = new PunkAttributes("Male", List.of("Happy", "Glasses"));
  private final Map<Integer, PunkAttributes> PUNKS_ATTRIBUTES = Map.of(
      1, punkAttributes
  );

  private Tuple5<Boolean, BigInteger, String, BigInteger, String> punkTuple =
      new Tuple5<>(true, BigInteger.ONE, "", BigInteger.ONE, "");
  private final Callable punkCallable = (Callable<Tuple5<Boolean, BigInteger, String, BigInteger, String>>) () -> punkTuple;
  private Tuple5<Boolean, BigInteger, String, BigInteger, String> punkTuple2 =
      new Tuple5<>(false, BigInteger.ONE, "", BigInteger.ONE, "");
  private final Callable punkCallable2 = (Callable<Tuple5<Boolean, BigInteger, String, BigInteger, String>>) () -> punkTuple2;
  private PunksManager punksManager;

  @Before
  public void init() {
    CryptoPunksMarket cryptoPunksMarket = Mockito.mock(CryptoPunksMarket.class);
    when(cryptoPunksMarket.punksOfferedForSale(any(BigInteger.class))).thenReturn(
        new RemoteFunctionCall(
            new Function("", new ArrayList<>(), new ArrayList<>()),
            punkCallable),
        new RemoteFunctionCall(
            new Function("", new ArrayList<>(), new ArrayList<>()),
            punkCallable2)
    );
    punksManager = new PunksManager(PUNKS_ATTRIBUTES, cryptoPunksMarket, RateLimiter.create(10000));

  }

  @Test
  public void testGetAllPunks() {
    List<Punk> punks = punksManager.getAllForSalePunks();

    assertEquals(punks.size(), 1);
  }

  @Test
  public void testGetIndividualPunk() {
    Punk punk = punksManager.getIndividualPunk(BigInteger.ONE);
    assertTrue(punk.getPunkAttributes().isPresent());
    assertEquals(punk.getPunkAttributes().get().gender, punkAttributes.gender);
    assertEquals(punk.getPunkAttributes().get().accessories, punkAttributes.accessories);
  }

  @Test
  public void punkTupleToPunkTest() {
    Punk punk = new Punk(punkTuple);

    assertTrue(punk.isForSale());
    assertEquals(punk.getIndex(), BigInteger.ONE);
    assertEquals(punk.getOnlySellTo(), "");
    assertEquals(punk.getPrice(), BigInteger.ONE);
    assertEquals(punk.getSeller(), "");
  }
}
