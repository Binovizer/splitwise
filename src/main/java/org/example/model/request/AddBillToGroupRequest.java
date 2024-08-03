package org.example.model.request;

import lombok.Data;
import org.example.model.Bill;

/**
 * The type AddBillToGroupRequest
 *
 * @author nadeem
 * Date : 03/08/24
 */
@Data
public class AddBillToGroupRequest {
    private Bill bill;
    private String groupId;
}
