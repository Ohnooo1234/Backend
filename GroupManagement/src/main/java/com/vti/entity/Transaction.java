package com.vti.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
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
import lombok.RequiredArgsConstructor;



@Entity
@Table(name = "Transaction")
@Data
@NoArgsConstructor
public class Transaction {
	private static final long serialVersionUID = 1L;

	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "amount", nullable = false)
	private int number_of_products;
	
	@Column(name = "transaction_date", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date transaction_date;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
	
	@OneToMany(mappedBy = "transaction")
	private List<Payment> payments;
}
