package com.h9drairawan.vanguard_backend.service.impl;

import com.h9drairawan.vanguard_backend.dto.request.ItemCreateRequest;
import com.h9drairawan.vanguard_backend.dto.request.ItemUpdateRequest;
import com.h9drairawan.vanguard_backend.dto.response.ItemResponse;
import com.h9drairawan.vanguard_backend.entity.Category;
import com.h9drairawan.vanguard_backend.entity.Item;
import com.h9drairawan.vanguard_backend.entity.User;
import com.h9drairawan.vanguard_backend.exception.DuplicateResourceException;
import com.h9drairawan.vanguard_backend.exception.ResourceNotFoundException;
import com.h9drairawan.vanguard_backend.mapper.ItemMapper;
import com.h9drairawan.vanguard_backend.repository.CategoryRepository;
import com.h9drairawan.vanguard_backend.repository.ItemRepository;
import com.h9drairawan.vanguard_backend.repository.UserRepository;
import com.h9drairawan.vanguard_backend.service.ItemService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Transactional
public class ItemServiceIml implements ItemService {
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ItemMapper itemMapper;

    public ItemServiceIml(ItemRepository itemRepository, UserRepository userRepository, CategoryRepository categoryRepository, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.itemMapper = itemMapper;
    }

    @Override
    public Page<ItemResponse> getAllItems(Pageable pageable) {
        Page<Item> page = itemRepository.findAll(pageable);
        return page.map(itemMapper::toResponse);
    }

    @Override
    public ItemResponse createItem(ItemCreateRequest dto) {
        if (itemRepository.existsByName(dto.name())) {
            throw new DuplicateResourceException("Item name " + dto.name() + " already exist");
        } else if (itemRepository.existsBySku(dto.sku())) {
            throw new DuplicateResourceException("Item sku " + dto.sku() + " already exist");
        }

        Category category = categoryRepository.findById(dto.categoryId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Category not found"
                        ));
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"
                        ));

        Item item = itemMapper.toEntity(dto);
        item.setCategory(category);
        item.setCreatedBy(user);
        item.setUpdatedBy(user);
        Item save = itemRepository.save(item);
        return itemMapper.toResponse(save);
    }

    @Override
    public ItemResponse updateItem(Long id, ItemUpdateRequest dto) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> (
                        new ResourceNotFoundException("Item not found")
                ));

        Category category = categoryRepository.findById(dto.categoryId())
                .orElseThrow(() -> (
                        new ResourceNotFoundException("Category not found")
                ));

        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> (
                        new ResourceNotFoundException("User not found")
                ));

        item.setName(dto.name());
        item.setSku(dto.sku());
        item.setDescription(dto.description());
        item.setPrice(dto.price());
        item.setStock(dto.stock());
        item.setCategory(category);
        item.setUpdatedBy(user);
        item.setUpdatedAt(LocalDateTime.now());
        itemRepository.save(item);
        return itemMapper.toResponse(item);
    }
}
