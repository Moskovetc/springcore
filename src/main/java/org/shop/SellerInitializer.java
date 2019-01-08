package org.shop;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.shop.api.SellerService;
import org.shop.data.Seller;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * The Seller Initializer util class.
 */
public class SellerInitializer {

    /** The seller service. */
    @Autowired
    private SellerService sellerService;
    
    /** The seller names. */
    @Autowired
    private Map<Long, String> sellerNames = Collections.emptyMap();

    /**
     * Inits the sellers.
     */
    public void initSellers() {
        List<Seller> sellers = new LinkedList<Seller>();
        
        for (Map.Entry<Long, String> entry : sellerNames.entrySet()) {
            Seller seller = new Seller();
            seller.setId(entry.getKey());
            seller.setName(entry.getValue());
            
            sellers.add(seller);
        }
        
        sellerService.importSellers(sellers);
    }
}
