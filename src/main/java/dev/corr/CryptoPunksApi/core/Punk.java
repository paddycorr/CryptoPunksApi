package dev.corr.CryptoPunksApi.core;

import org.web3j.tuples.generated.Tuple5;

import java.math.BigInteger;
import java.util.Optional;

public class Punk {
  private boolean isForSale;
  private BigInteger index;
  private String seller;
  private BigInteger price;
  private String onlySellTo;
  private Optional<PunkAttributes> punkAttributes;

  public Punk(Tuple5<Boolean, BigInteger, String, BigInteger, String> tuple) {
    this.isForSale = tuple.component1();
    this.index = tuple.component2();
    this.seller = tuple.component3();
    this.price = tuple.component4();
    this.onlySellTo = tuple.component5();
  }

  public boolean isForSale() {
    return isForSale;
  }

  public BigInteger getIndex() {
    return index;
  }

  public Punk setIndex(BigInteger index) {
    this.index = index;
    return this;
  }

  public String getSeller() {
    return seller;
  }

  public BigInteger getPrice() {
    return price;
  }

  public String getOnlySellTo() {
    return onlySellTo;
  }

  public Optional<PunkAttributes> getPunkAttributes() {
    return punkAttributes;
  }

  public Punk setPunkAttributes(Optional<PunkAttributes> punkAttributes) {
    this.punkAttributes = punkAttributes;
    return this;
  }

  public Punk setPunkAttributes(PunkAttributes punkAttributes) {
    this.punkAttributes = Optional.of(punkAttributes);
    return this;
  }

}
