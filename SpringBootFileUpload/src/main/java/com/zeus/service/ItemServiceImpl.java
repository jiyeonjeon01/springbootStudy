package com.zeus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zeus.domain.Item;
import com.zeus.mapper.ItemMapper;

@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private ItemMapper mapper;

	@Override
	public List<Item> list() throws Exception {
		return mapper.list();
	}

	@Override
	public void regist(Item item) throws Exception {
		mapper.create(item);

	}

	@Override
	public Item read(Integer itemId) throws Exception {
		return mapper.read(itemId);
	}

	@Override
	public void modify(Item item) throws Exception {
		mapper.update(item);

	}

	@Override
	public void remove(Integer itemId) throws Exception {
		mapper.delete(itemId);

	}

	@Override
	public String getPicture(Integer itemId) throws Exception {
		return mapper.getPicture(itemId);
	}
}
