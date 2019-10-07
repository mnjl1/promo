package com.mmplus.promo.service;

import com.mmplus.promo.domain.Item;
import java.util.List;
import java.util.Optional;

public interface ItemService {

    Optional<Item> findById(Long id);

    void saveOrUpdate(Item item);

    List<Item> findAll();
}
