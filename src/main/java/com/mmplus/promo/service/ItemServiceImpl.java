package com.mmplus.promo.service;

import com.mmplus.promo.domain.Item;
import com.mmplus.promo.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Optional<Item> findById(Long id) {
        return itemRepository.findById(id);
    }

    @Override
    public void saveOrUpdate(Item item) {
        itemRepository.save(item);
    }

    @Override
    public List<Item> findAll() {
        return (List<Item>) itemRepository.findAll();
    }

    @Override
    public boolean eanExists(String ean) {
        Item item = itemRepository.findByEan(ean);
        return item != null;
    }

    @Override
    public Item findItemByEan(String ean) {
        return itemRepository.findByEan(ean);
    }
}
