package miu.cs545.auctionsystem.service.implService;

import miu.cs545.auctionsystem.service.BiddingProductService;
import miu.cs545.auctionsystem.model.BiddingProduct;
import miu.cs545.auctionsystem.repository.BiddingProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BiddingProductServiceImpl implements BiddingProductService {

    private BiddingProductRepo biddingProductRepo;

    @Autowired
    public BiddingProductServiceImpl(BiddingProductRepo biddingProductRepo){
        this.biddingProductRepo=biddingProductRepo;
    }

    @Override
    public void bidOnProduct(Integer id){
        BiddingProduct biddingProduct = getBiddingProductById(id);
    }

    @Override
    public List<BiddingProduct> findBiddingProductByStatusTrue() {
        return biddingProductRepo.findByStatusTrue();
    }

    @Override
    public List<BiddingProduct> getBiddingProducts() {
        return biddingProductRepo.findAll();
    }

    @Override
    public BiddingProduct getBiddingProductById(Integer id) {
        return biddingProductRepo.findById(id).orElse(null);
    }

    @Override
    public void deleteBiddingProductById(Integer id) {
        biddingProductRepo.deleteById(id);
    }

    @Override
    public BiddingProduct addBiddingProduct(BiddingProduct product) {
        return biddingProductRepo.save(product);
    }

    @Override
    public BiddingProduct updateBiddingProduct(BiddingProduct product) {
        //BiddingProduct biddingProduct = getBiddingProductById(product.getId());
        //biddingProduct.
        return biddingProductRepo.save(product);
    }
}
