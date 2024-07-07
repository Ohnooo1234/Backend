package com.vti.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;



@Entity
@Table(name = "Payment")
@Data
@NoArgsConstructor
public class Payment {
	private static final long serialVersionUID = 1L;

	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "payment_method", nullable = false)
	private PaymentMethod payment_method ;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "payment_status", nullable = false)
	private PaymentStatus payment_status = PaymentStatus.PENDING;
	
	@Column(name = "payment_date", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date payment_date;
	
	@ManyToOne
	@JoinColumn(name = "transaction_id")
	private Transaction transaction;
	
}
