package org.example.model.request;

import lombok.Data;

/**
 * The type RemoveUserFromGroupRequest
 *
 * @author nadeem
 * Date : 03/08/24
 */
@Data
public class RemoveUserFromGroupRequest {
    private String userId;
    private String groupId;
}
