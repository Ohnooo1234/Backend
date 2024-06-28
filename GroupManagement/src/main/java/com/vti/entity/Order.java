package com.vti.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "Order")
@Data
@NoArgsConstructor
public class Order {
	private static final long serialVersionUID = 1L;

	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "address", length = 500, nullable = false)
	private String address;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "status", nullable = false)
	private OrderStatus status = OrderStatus.PENDING;
	
	@Column(name = "total_amount", nullable = false)
	private int total_amount;
	
	@Column(name = "created_at", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date created_at;
	
	@OneToMany(mappedBy = "order")
	private List<OrderDetail> orderDetails;
	
	@OneToMany(mappedBy = "order")
	private List<Transaction> transactions;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

}
