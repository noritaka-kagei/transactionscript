package transactionscript.controller;

import transactionscript.model.User;
import transactionscript.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// GET /users - 全てのユーザーを取得
// GET /users/{id} - IDでユーザーを取得
// POST /users - 新しいユーザーを作成
// PUT /users/{id} - ユーザーを更新
// DELETE /users/{id} - ユーザーを削除

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService svc;

    @GetMapping
    public List<User> getAllUsers() {
        return svc.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return svc.findById(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return svc.save(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        User user = svc.findById(id);
        if (user != null) {
            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());
            user.setAddress(userDetails.getAddress());
            user.setPhone(userDetails.getPhone());
            user.setPostalCode(userDetails.getPostalCode());
            user.setBirthDate(userDetails.getBirthDate());
            user.setCreatedAt(userDetails.getCreatedAt());
            return svc.save(user);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        svc.deleteById(id);
    }
}
