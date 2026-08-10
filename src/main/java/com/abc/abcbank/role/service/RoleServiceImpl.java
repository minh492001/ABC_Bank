package com.abc.abcbank.role.service;

import com.abc.abcbank.exceptions.BadRequestException;
import com.abc.abcbank.exceptions.NotFoundException;
import com.abc.abcbank.response.Response;
import com.abc.abcbank.role.entity.Role;
import com.abc.abcbank.role.repo.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService{

    private final RoleRepository roleRepository;

    @Override
    public Response<Role> createRole(Role roleRequest) {
        if (roleRepository.findByName(roleRequest.getName()).isPresent()) {
            throw new BadRequestException("Role already exist");
        }
        Role savedRole = roleRepository.save(roleRequest);

        return Response.<Role>builder() // Explicit Type Argument for Generic Methods
                .statusCode(HttpStatus.CREATED.value()) // 201 Created
                .message("Role saved successfully")
                .data(savedRole)
                .build();
    }

    @Override
    public Response<Role> updateRole(Role roleRequest) {
        Role role = roleRepository.findById(roleRequest.getId())
                .orElseThrow(() -> new NotFoundException("Role not found"));
        role.setName(roleRequest.getName());
        Role updatedRole = roleRepository.save(role);

        return Response.<Role>builder() // Explicit Type Argument for Generic Methods
                .statusCode(HttpStatus.CREATED.value()) // 201 Created
                .message("Role updated successfully")
                .data(updatedRole)
                .build();
    }

    @Override
    public Response<List<Role>> getAllRoles() {
        List<Role> roles = roleRepository.findAll();

        return Response.<List<Role>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Roles retrieved successfully")
                .data(roles)
                .build();
    }

    @Override
    public Response<?> deleteRole(Long id) {
        if (!roleRepository.existsById(id)) {
            throw new NotFoundException("Role Not Found");
        }
        roleRepository.deleteById(id);

        return Response.builder()
                .statusCode(HttpStatus.OK.value())
                .message("Role deleted successfully")
                .build();
    }
}
