package io.pivotal.pad.cronos.domain;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.hateoas.Link;

@Table(value = "product_rankings")
public class ProductRanking {

	@PrimaryKey
	private ProductRankingKey id;

	@Column(value = "sales_rank")
	private int salesRank;
	
	private Link link;

	public int getSalesRank() {
		return salesRank;
	}

	public void setSalesRank(int salesRank) {
		this.salesRank = salesRank;
	}

}
