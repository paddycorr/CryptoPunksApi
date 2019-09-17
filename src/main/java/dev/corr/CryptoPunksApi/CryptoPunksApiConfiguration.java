package dev.corr.CryptoPunksApi;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

public class CryptoPunksApiConfiguration extends Configuration {

  @NotEmpty
  private String ethereumEndpoint;
  @NotEmpty
  private String contractAddress;
  @NotEmpty
  private String privateKey;

  @JsonProperty("ethereum.endpoint")
  public String getEthereumEndpoint() {
    return ethereumEndpoint;
  }

  @JsonProperty("ethereum.endpoint")
  public void setEthereumEndpoint(String ethereumEndpoint) {
    this.ethereumEndpoint = ethereumEndpoint;
  }

  @JsonProperty("contract.address")
  public String getContractAddress() {
    return contractAddress;
  }

  @JsonProperty("contract.address")
  public void setContractAddress(String contractAddress) {
    this.contractAddress = contractAddress;
  }

  @JsonProperty("private.key")
  public String getPrivateKey() {
    return privateKey;
  }

  @JsonProperty("private.key")
  public void setPrivateKey(String privateKey) {
    this.privateKey = privateKey;
  }


}
