package com.nauticana.shipment.repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.basis.utils.Utils;
import com.nauticana.shipment.model.ViewShipmentLineStatus;

@Repository
public class ShipmentJbdcRepository {

    private static final String shipmentLineStatus    =
            "SELECT S.SHIPMENT_ID, S.SHIPMENT_TYPE, S.SHIP_DATE, S.STATUS, S.USERNAME, S.INVOICE_ID, S.FROM_ADDRESS, S.FROM_CITY, S.FROM_STATE, S.FROM_COUNTRY, S.TO_ADDRESS, S.TO_CITY, S.TO_STATE, S.TO_COUNTRY, L.MATERIAL_ID, M.CAPTION, L.QUANTITY, L.UNIT, L.REF_ORDER_TYPE, l.REF_ORDER_ID" +
                    "  FROM MATERIAL M, SHIPMENT_LINE L, SHIPMENT S" +
                    " WHERE M.MATERIAL_ID=L.MATERIAL_ID " +
                    "   AND S.OWNER_ID = ?" +
                    "   AND M.OWNER_ID = ?" +
                    "   AND S.STATUS = ?" +
                    "   AND S.BEGDA BETWEEN ? AND ? " +
                    "   AND L.SHIPMENT_ID = S.SHIPMENT_ID" +
                    " ORDER BY M.CAPTION ";

    @Autowired
    private JdbcTemplate j;

    class ShipmentLineStatusRowMapper implements RowMapper<ViewShipmentLineStatus> {
        @Override
        public ViewShipmentLineStatus mapRow(ResultSet rs, int rowNum) throws SQLException {

            int        shipmentId     = rs.getInt(1);
            char       shipmentType   = rs.getString(2).charAt(0);
            Date       shipDate       = rs.getDate(3);
            char       status         = rs.getString(4).charAt(0);
            String     username       = rs.getString(5);
            String     invoiceId      = rs.getString(6);
            String     sourceAddress  = rs.getString(7) + " " + rs.getString(8) + " " + rs.getString(9) + " " + rs.getString(10);
            String     destAddress    = rs.getString(11) + " " + rs.getString(12) + " " + rs.getString(13) + " " + rs.getString(14);
            int        materialId     = rs.getInt(15);
            String     caption        = rs.getString(16);
            BigDecimal quantity       = rs.getBigDecimal(17);
            String     unit           = rs.getString(18);
            Character  refOrderType   = null;
            Integer    refOrderId     = null;
            String x = rs.getString(19);
            if (!Utils.emptyStr(x)) refOrderType = x.charAt(0);
            x = rs.getString(20);
            if (!Utils.emptyStr(x)) refOrderId = Integer.parseInt(x);

            return new ViewShipmentLineStatus(shipmentId, shipmentType, shipDate, status, username, invoiceId, sourceAddress, destAddress, materialId, caption, quantity, unit, refOrderType, refOrderId);

        }
    }

    @Transactional(readOnly=true)
    public List<ViewShipmentLineStatus> getShipmentLineStatus(int client, Date begda, Date endda, String status) {
        return j.query(shipmentLineStatus, new ArgumentPreparedStatementSetter(new Object[]{client, client, status, begda, endda}), new ShipmentLineStatusRowMapper());
    }

}
