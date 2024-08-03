package org.example.model.request;

import lombok.Builder;
import lombok.Data;

/**
 * The type CreateGroupRequest
 *
 * @author nadeem
 * Date : 03/08/24
 */
@Data
@Builder
public class CreateGroupRequest {
    private String name;
    private String description;
}
