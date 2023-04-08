package com.cscb847f89497.kursovarabota.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cscb847f89497.kursovarabota.dto.EditorDTO;
import com.cscb847f89497.kursovarabota.entity.Editor;
import com.cscb847f89497.kursovarabota.entity.Role;
import com.cscb847f89497.kursovarabota.entity.User;
import com.cscb847f89497.kursovarabota.repository.EditorRepository;
import com.cscb847f89497.kursovarabota.repository.RoleRepository;
import com.cscb847f89497.kursovarabota.repository.UserRepository;
import com.cscb847f89497.kursovarabota.service.EditorService;
import com.cscb847f89497.kursovarabota.utils.Utils;

@Service
public class EditorServiceImpl implements EditorService {

    @Autowired
    private EditorRepository editorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public EditorDTO findOneById(Long id) {
        Editor editor = editorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ivalid id: " + id));
        return Utils.mapEditorToEditorDTO(editor);
    }

    public EditorDTO findOneByUsername(String username) {
        Editor editor = editorRepository.findOneByUsername(username);
        return Utils.mapEditorToEditorDTO(editor);
    }

    @Override
    public List<EditorDTO> list() {
        return Utils.mapListEditorDTOs(editorRepository.findAll());
    }

    @Override
    public void add(EditorDTO editorDTO) {
        ModelMapper mapper = new ModelMapper();
        User user = new User();
        user.setEnabled(true);
        user.setPassword("$2a$12$/2JuC//y4ViP2rsHDmY/pugPZ2CvDgZN2fxQbEgr2QTlQLukp3A5e");
        if (editorDTO.getUsername() != null) {
            user.setUsername(editorDTO.getUsername());
            editorDTO.setUsername(editorDTO.getUsername());
        } else {
            user.setUsername(editorDTO.getEmail());
            editorDTO.setUsername(editorDTO.getEmail());
        }
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findOneByName("EDITOR"));
        editorRepository.save(mapper.map(editorDTO, Editor.class));
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        Editor editor = editorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ivalid id: " + id));
        User user = userRepository.getUserByUsername(editor.getUsername());
        user.setEnabled(false);
        userRepository.save(user);
        editorRepository.deleteById(id);
    }

    @Override
    public void update(EditorDTO editorDTO) {
        ModelMapper mapper = new ModelMapper();
        Editor oldEditor = editorRepository.findById(editorDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Ivalid id: " + editorDTO.getId()));
        oldEditor = mapper.map(editorDTO, Editor.class);
        if (editorDTO.getPassword() != null) {
            User user = userRepository.findByUsername(editorDTO.getUsername());
            user.setPassword(passwordEncoder.encode(editorDTO.getPassword()));
            userRepository.save(user);
        }
        editorRepository.save(oldEditor);
    }

}
