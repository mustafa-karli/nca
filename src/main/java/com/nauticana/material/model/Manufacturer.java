package com.nauticana.material.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.nauticana.purchase.model.RequestForProposalItem;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = Manufacturer.TABLE_NAME)
public class Manufacturer implements java.io.Serializable {

	private static final long     serialVersionUID = 1L;
	public  static final String   ROOTMAPPING = "manufacturer";
	public  static final String   TABLE_NAME  = "MANUFACTURER";

	@Id
	@Column(name = "MANUFACTURER_ID", unique = true, nullable = false, length = 30)
	private String id;

	@Column(name = "CAPTION", nullable = false, length = 80)
	private String caption;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "manufacturer")
	private Set<Material> materials = new HashSet<Material>(0);

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "manufacturer")
	private Set<RequestForProposalItem> requestedManufactures = new HashSet<RequestForProposalItem>(0);
}
