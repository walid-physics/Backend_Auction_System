package miu.cs545.auctionsystem.service.implService;

import lombok.RequiredArgsConstructor;
import miu.cs545.auctionsystem.model.BiddingProduct;
import miu.cs545.auctionsystem.model.BiddingSystem;
import miu.cs545.auctionsystem.repository.BiddingSystemRepo;
import miu.cs545.auctionsystem.service.BiddingProductService;
import miu.cs545.auctionsystem.service.BiddingSystemService;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BiddingSystemServiceImpl implements BiddingSystemService {

    private final BiddingSystemRepo biddingSystemRepo;
    private final BiddingProductService biddingProductService;


    @Override
    public void bidOnProduct(Integer userId, Integer biddingProductId, Double amount) {
        BiddingProduct biddingProduct = biddingProductService.getBiddingProductById(biddingProductId);
        //bidd
    }

    @Override
    public BiddingSystem getBiddingSystemById(Integer id) {
        return biddingSystemRepo.findById(id).orElse(null);
    }

    @Override
    public List<BiddingSystem> getAllBiddingSystems() {
        return biddingSystemRepo.findAll();
    }

    @Override
    public void deleteBiddingSystemById(Integer id) {
        biddingSystemRepo.deleteById(id);
    }

    @Override
    public BiddingSystem updateBiddingSystem(BiddingSystem biddingSystem) {
        BiddingSystem biddingSystem1 = getBiddingSystemById(biddingSystem.getId());
        biddingSystem1.setBiddingProduct(biddingSystem.getBiddingProduct());
        biddingSystem1.setBidders(biddingSystem.getBidders());
        return biddingSystem1;
    }

    @Override
    public BiddingSystem saveBiddingSystem(BiddingSystem biddingSystem) {
        return biddingSystemRepo.save(biddingSystem);
    }
}
