package org.example.model.request;

import lombok.Builder;
import lombok.Data;

/**
 * The type CreateUserRequest
 *
 * @author nadeem
 * Date : 03/08/24
 */
@Data
@Builder
public class CreateUserRequest {
    private String name;
    private String email;
}
