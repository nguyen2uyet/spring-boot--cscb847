package com.cscb847f89497.kursovarabota.service;

import java.util.List;

import com.cscb847f89497.kursovarabota.dto.EditorDTO;

public interface EditorService {
    public EditorDTO findOneById(Long id);

    public List<EditorDTO> list();

    public void add(EditorDTO EditorDTO);

    public void delete(Long id);

    public void update(EditorDTO EditorDTO);

    public EditorDTO findOneByUsername(String username);
}
