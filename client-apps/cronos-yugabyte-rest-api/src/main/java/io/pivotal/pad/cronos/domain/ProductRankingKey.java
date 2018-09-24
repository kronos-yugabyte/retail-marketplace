package io.pivotal.pad.cronos.domain;

import java.io.Serializable;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

@PrimaryKeyClass
public class ProductRankingKey implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6646128061564873843L;

	@PrimaryKeyColumn(name = "asin", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	private String id;
	
	@PrimaryKeyColumn(name = "category", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
	private String category;

	public String getId() {
		return id;
	}

	public String getCategory() {
		return category;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	@Override
	  public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((id == null) ? 0 : id.hashCode());
	    result = prime * result + ((id == null) ? 0 : id.hashCode());
	    return result;
	  }
	  
	  @Override
	  public boolean equals(Object obj) {
	    if (this == obj)
	      return true;
	    if (obj == null)
	      return false;
	    if (getClass() != obj.getClass())
	      return false;
	    ProductRankingKey other = (ProductRankingKey) obj;
	    if (id == null) {
	      if (other.id != null)
	        return false;
	      else {
	    	  if (!id.equals(other.id)) {
	    		  return false;
	    	  }
	      }
	    }
	    return true;
	  }

}
