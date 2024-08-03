package org.example.model.request;

import lombok.Builder;
import lombok.Data;

/**
 * The type AddUserToGroupRequest
 *
 * @author nadeem
 * Date : 03/08/24
 */
@Data
@Builder
public class AddUserToGroupRequest {
    private String userId;
    private String groupId;
}
