package com.kh.domain;

import lombok.Builder;

@Builder
public class Person {
	private final String name;
	private final int age;
	private final int phone; 
}