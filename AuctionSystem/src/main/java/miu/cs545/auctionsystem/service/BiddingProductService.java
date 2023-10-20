package miu.cs545.auctionsystem.service;

import miu.cs545.auctionsystem.model.BiddingProduct;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface BiddingProductService {

    public void bidOnProduct(Integer id);
    public List<BiddingProduct> findBiddingProductByStatusTrue();

    public List<BiddingProduct> getBiddingProducts();
    public BiddingProduct getBiddingProductById(Integer id);

    public void deleteBiddingProductById(Integer id);
    public BiddingProduct addBiddingProduct(BiddingProduct product);
    public BiddingProduct updateBiddingProduct(BiddingProduct product);
}
