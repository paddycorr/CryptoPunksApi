package dev.corr.CryptoPunksApi.resources;

import dev.corr.CryptoPunksApi.core.Punk;
import dev.corr.CryptoPunksApi.data.PunksManager;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.math.BigInteger;
import java.util.List;

@Path("/crypto-punks")
@Produces(MediaType.APPLICATION_JSON)
public class CryptoPunks {

  private int MAX = 9999;
  private PunksManager punksManager;

  public CryptoPunks(PunksManager punksManager) {
    this.punksManager = punksManager;
  }

  @GET
  public List<Punk> getForSalePunks() {
    return punksManager.getAllForSalePunks();
  }

  @GET
  @Path("/{punkId}")
  public Punk getIndividualPunk(@PathParam("punkId") BigInteger punkId) {
    if (punkId.intValue() > MAX || punkId.intValue() < 0) {
      throw new NotFoundException("PunkId must between 0 and" + MAX);
    }
    return punksManager.getIndividualPunk(punkId);
  }
}
