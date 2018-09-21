package io.pivotal.pad.cronos.domain;

import java.io.Serializable;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Table(value = "product_rankings")
public class ProductRanking implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7003313441709838894L;

	@PrimaryKeyColumn(name = "asin", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	private String id;
	
	@PrimaryKeyColumn(name = "category", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
	private String category;
	
	@Column(value = "sales_rank")
	private int salesRank;

	public String getCategory() {
		return category;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getSalesRank() {
		return salesRank;
	}

	public void setSalesRank(int salesRank) {
		this.salesRank = salesRank;
	}
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductRanking product = (ProductRanking) o;

        return id.equals(product.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }


}
