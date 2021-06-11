package com.nauticana.personnel.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = PositionType.TABLE_NAME)
public class PositionType implements java.io.Serializable {

	private static final long     serialVersionUID = 1L;
	public  static final String   ROOTMAPPING = "positionType";
	public  static final String   TABLE_NAME  = "POSITION_TYPE";

	@EmbeddedId
	@AttributeOverrides({
		@AttributeOverride(name = "ownerId", column = @Column(name = "OWNER_ID", nullable = false)),
		@AttributeOverride(name = "position", column = @Column(name = "POSITION", nullable = false, length = 20)) })
	private PositionTypeId id;

	@Column(name = "CAPTION", nullable = false, length = 80)
	private String caption;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "positionType")
	private Set<Position> positions = new HashSet<Position>(0);
}