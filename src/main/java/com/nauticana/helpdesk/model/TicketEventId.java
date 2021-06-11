package com.nauticana.helpdesk.model;

import java.text.ParseException;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.springframework.format.annotation.DateTimeFormat;

import com.nauticana.basis.utils.Labels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class TicketEventId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "TICKET_ID", nullable = false)
	private int ticketId;

	@DateTimeFormat(pattern = Labels.dmyFullStr)
	@Column(name = "EVENT_DATE", nullable = false, length = 26)
	private Date eventDate;

	public TicketEventId(String keys) {
		String[] s = keys.split(",");
		this.ticketId = Integer.parseInt(s[0]);
		try {
			this.eventDate = Labels.dmyDF.parse(s[1]);
		} catch (ParseException e) {
			this.eventDate = new Date(System.currentTimeMillis());
		}
	}

	@Override
	public String toString() {
		return this.ticketId + "," + Labels.dmyDF.format(eventDate);
	}
}
