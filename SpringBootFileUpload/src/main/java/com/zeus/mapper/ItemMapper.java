package com.zeus.mapper;

import java.util.List;

import com.zeus.domain.Item;

public interface ItemMapper {
	public void create(Item item) throws Exception;

	public Item read(Integer itemId) throws Exception;

	public void update(Item item) throws Exception;

	public void delete(Integer itemId) throws Exception;

	public List<Item> list() throws Exception;

	public String getPicture(Integer itemId) throws Exception;
}
