package com.cscb847f89497.kursovarabota.utils;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.cscb847f89497.kursovarabota.dto.ArticleDTO;
import com.cscb847f89497.kursovarabota.dto.EditorDTO;
import com.cscb847f89497.kursovarabota.dto.StudentDTO;
import com.cscb847f89497.kursovarabota.dto.UserDTO;
import com.cscb847f89497.kursovarabota.entity.Article;
import com.cscb847f89497.kursovarabota.entity.Editor;
import com.cscb847f89497.kursovarabota.entity.Student;
import com.cscb847f89497.kursovarabota.entity.User;
import com.cscb847f89497.kursovarabota.repository.UserRepository;

@Component
public class Utils {

    private static PasswordEncoder passwordEncoder;

    @Autowired
    private PasswordEncoder passwordEncoder0;

    private static UserRepository userRepository;

    @Autowired
    private UserRepository userRepository0;

    @PostConstruct
    private void initStaticDao() {
        userRepository = this.userRepository0;
        passwordEncoder = this.passwordEncoder0;
    }

    public static Student mapUserDtoToStudent(UserDTO userDTO) {
        Student student = new Student();
        student.setAge(0);
        student.setName("");
        student.setEmail(userDTO.getEmail());
        student.setUsername(userDTO.getUsername());
        student.setTelephone("");
        return student;
    }

    public static EditorDTO mapEditorToEditorDTO(Editor editor) {
        ModelMapper modelMapper = new ModelMapper();
        EditorDTO editorDTO = modelMapper.map(editor, EditorDTO.class);
        return editorDTO;
    }

    public static List<EditorDTO> mapListEditorDTOs(List<Editor> editors) {
        return editors
                .stream()
                .map(e -> mapEditorToEditorDTO(e))
                .collect(Collectors.toList());
    }

    public static <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        ModelMapper modelMapper = new ModelMapper();
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }

    public static Student mapStudentDTOToStudent(StudentDTO studentDTO, Student oldStudent) {
        ModelMapper modelMapper = new ModelMapper();
        Student student = modelMapper.map(studentDTO, Student.class);
        student.setUsername(oldStudent.getUsername());
        if (studentDTO.getPassword() != null) {
            User user = userRepository.findByUsername(oldStudent.getUsername());
            user.setPassword(passwordEncoder.encode(studentDTO.getPassword()));
            userRepository.save(user);
        }
        return student;
    }

    public static ArticleDTO mapArticleToArticleDTO(Article article) {
        ModelMapper modelMapper = new ModelMapper();
        ArticleDTO articleDTO = modelMapper.map(article, ArticleDTO.class);
        return articleDTO;
    }

    public static List<ArticleDTO> mapListArticleDTOs(List<Article> articles) {
        return articles
                .stream()
                .map(e -> mapArticleToArticleDTO(e))
                .collect(Collectors.toList());
    }

}
