package com.nauticana.basis.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.nauticana.basis.utils.Labels;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = ContentData.TABLE_NAME)
public class ContentData implements java.io.Serializable {

	private static final long     serialVersionUID = 1L;
	public  static final String   ROOTMAPPING = "contentData";
	public  static final String   TABLE_NAME  = "CONTENT_DATA";
	public  static final String   SEQUENCE_NAME = "CONTENT_DATA_ID_SEQ";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	@SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize=1)
	@Column(name = "CONTENT_ID", unique = true, nullable = false)
	private long id;

	@Column(name = "OWNER_ID", nullable = false)
	private int ownerId;

	@Column(name = "ORIGINAL_FILE", nullable = false, length = 250)
	private String originalFile;

	@Column(name = "ORIGIN_IP", nullable = false, length = 32)
	private String originIp;

	@Column(name = "MIMETYPE", nullable = false, length = 20)
	private String mimetype;

	@Column(name = "USERNAME", nullable = false, length = 30)
	private String username;

	@DateTimeFormat(pattern = Labels.dmyFullStr)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME", nullable = false, length = 26)
	private Date createTime;

	@Column(name = "BINDATA")
	private byte[] bindata;

	@Column(name = "THUMB")
	private byte[] thumb;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "contentData")
	private Set<ContentRelation> contentRelations = new HashSet<ContentRelation>(0);
}
