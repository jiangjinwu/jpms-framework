package io.sited.user.web;

import com.google.common.base.Splitter;
import io.sited.user.api.UserGroupWebService;
import io.sited.user.api.group.BatchDeleteUserGroupRequest;
import io.sited.user.api.group.BatchGetRequest;
import io.sited.user.api.group.CreateUserGroupRequest;
import io.sited.user.api.group.UpdateUserGroupRequest;
import io.sited.user.api.group.UserGroupQuery;
import io.sited.user.api.group.UserGroupResponse;
import io.sited.user.domain.UserGroup;
import io.sited.user.service.UserGroupService;
import io.sited.util.collection.QueryResponse;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author chi
 */
public class UserGroupWebServiceImpl implements UserGroupWebService {
    @Inject
    UserGroupService userGroupService;

    @Override
    public UserGroupResponse get(String id) {
        return response(userGroupService.get(id));
    }

    @Override
    public Optional<UserGroupResponse> findByName(String name) {
        Optional<UserGroup> userGroup = userGroupService.findByName(name);
        return userGroup.map(this::response);
    }

    @Override
    public List<UserGroupResponse> batchGet(BatchGetRequest request) {
        return userGroupService.batchGet(request.ids).stream().map(this::response).collect(Collectors.toList());
    }

    @Override
    public QueryResponse<UserGroupResponse> find(UserGroupQuery query) {
        return userGroupService.find(query).map(this::response);
    }

    @Override
    public List<UserGroupResponse> findAll() {
        return userGroupService.find().stream().map(this::response).collect(Collectors.toList());
    }

    @Override
    public UserGroupResponse create(CreateUserGroupRequest request) {
        return response(userGroupService.create(request));
    }

    @Override
    public UserGroupResponse update(String id, UpdateUserGroupRequest request) {
        return response(userGroupService.update(id, request));
    }

    @Override
    public void batchDelete(BatchDeleteUserGroupRequest request) {
        request.ids.forEach(id -> userGroupService.delete(id, request.requestBy));
    }

    @Override
    public void revert(String id, String requestBy) {
        userGroupService.revert(id, requestBy);
    }

    private UserGroupResponse response(UserGroup userGroup) {
        UserGroupResponse response = new UserGroupResponse();
        response.id = userGroup.id;
        response.name = userGroup.name;
        response.status = userGroup.status;
        response.description = userGroup.description;
        response.roles = Splitter.on(",").splitToList(userGroup.roles);
        response.createdTime = userGroup.createdTime;
        response.createdBy = userGroup.createdBy;
        response.updatedTime = userGroup.updatedTime;
        response.updatedBy = userGroup.updatedBy;
        return response;
    }
}
