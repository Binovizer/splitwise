package org.example.model.response;

import lombok.Data;
import org.example.model.User;

import java.util.List;

/**
 * The type CreateGroupResponse
 *
 * @author nadeem
 * Date : 03/08/24
 */
@Data
public class CreateGroupResponse {
    private String id;
    private String name;
    private String description;
    private List<User> members;
}
