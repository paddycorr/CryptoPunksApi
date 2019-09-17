package dev.corr.CryptoPunksApi.core;

import java.util.List;

public class PunkAttributes {
  public String gender;
  public List<String> accessories;

  public PunkAttributes(String gender, List<String> accessories) {
    this.gender = gender;
    this.accessories = accessories;
  }

  public PunkAttributes() {

  }
}
