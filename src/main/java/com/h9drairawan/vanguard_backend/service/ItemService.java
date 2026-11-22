package com.h9drairawan.vanguard_backend.service;

import com.h9drairawan.vanguard_backend.dto.request.ItemCreateRequest;
import com.h9drairawan.vanguard_backend.dto.request.ItemUpdateRequest;
import com.h9drairawan.vanguard_backend.dto.response.ItemResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemService {
    Page<ItemResponse> getAllItems(Pageable pageable);

    ItemResponse createItem(ItemCreateRequest dto);

    ItemResponse updateItem(Long id, ItemUpdateRequest dto);
}
