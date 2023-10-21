package miu.cs545.auctionsystem.service;

import miu.cs545.auctionsystem.model.BiddingProduct;
import miu.cs545.auctionsystem.model.BiddingSystem;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


public interface BiddingSystemService {
    public void bidOnProduct( Integer userId,  Integer biddingProductId,
                             Double amount);
    public BiddingSystem getBiddingSystemById(Integer id);
    public List<BiddingSystem> getAllBiddingSystems();
    public void deleteBiddingSystemById(Integer id);
    public BiddingSystem updateBiddingSystem(BiddingSystem biddingSystem);
    public BiddingSystem saveBiddingSystem(BiddingSystem biddingSystem);

}
