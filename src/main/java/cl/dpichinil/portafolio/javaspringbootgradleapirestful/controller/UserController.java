package cl.dpichinil.portafolio.javaspringbootgradleapirestful.controller;

import cl.dpichinil.portafolio.javaspringbootgradleapirestful.dto.UserDto;
import cl.dpichinil.portafolio.javaspringbootgradleapirestful.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/insert")
    public ResponseEntity<UserDto> insert(@RequestBody UserDto dto){
        ResponseEntity<UserDto> response = null;
        dto = userService.insertUser(dto);
        response = new ResponseEntity<UserDto>(dto, HttpStatus.OK);
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable("id") int id){
        ResponseEntity<UserDto> response = null;
        UserDto dto = userService.getUserById(id);
        response = new ResponseEntity<UserDto>(dto, HttpStatus.OK);
        return response;
    }
}
