package com.efm.orderstore.domains;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable //subtipo
public class OrderItemPK implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name="ordercli_id")
	private OrderCli orderCli;
	
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;

	public OrderCli getOrderCli() {
		return orderCli;
	}

	public void setOrderCli(OrderCli orderCli) {
		this.orderCli = orderCli;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderCli == null) ? 0 : orderCli.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
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
		OrderItemPK other = (OrderItemPK) obj;
		if (orderCli == null) {
			if (other.orderCli != null)
				return false;
		} else if (!orderCli.equals(other.orderCli))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}

}
