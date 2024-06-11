package repository.impl;

import aston.project.model.Role;
import aston.project.repository.RoleRepository;
import aston.project.repository.impl.RoleRepositoryImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.ext.ScriptUtils;
import org.testcontainers.jdbc.JdbcDatabaseDelegate;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import aston.project.util.PropertiesUtil;

import java.util.Optional;

@Testcontainers
class RoleRepositoryImplTest {
    private static final String INIT_SQL = "sql/schema.sql";
    private static final int containerPort = 5432;
    @Container
    public static PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:15-alpine")
            .withDatabaseName("users_db")
            .withUsername(PropertiesUtil.getProperty("db.username"))
            .withPassword(PropertiesUtil.getProperty("db.password"))
            .withExposedPorts(containerPort)
            .withInitScript(INIT_SQL);
    public static RoleRepository roleRepository;
    private static JdbcDatabaseDelegate jdbcDatabaseDelegate;

    @BeforeAll
    static void beforeAll() {
        container.start();
        roleRepository = RoleRepositoryImpl.getInstance();
        jdbcDatabaseDelegate = new JdbcDatabaseDelegate(container, "");
    }

    @AfterAll
    static void afterAll() {
        container.stop();
    }

    @BeforeEach
    void setUp() {
        ScriptUtils.runInitScript(jdbcDatabaseDelegate, INIT_SQL);
    }

    @Test
    void save() {
        String expectedName = "new Role Name";
        Role role = new Role(null, expectedName);
        role = roleRepository.save(role);
        Optional<Role> resultRole = roleRepository.findById(role.getId());

        Assertions.assertTrue(resultRole.isPresent());
        Assertions.assertEquals(expectedName, resultRole.get().getName());
    }

    @Test
    void update() {
        String expectedName = "UPDATE Role Name";

        Role roleForUpdate = roleRepository.findById(3L).get();
        String oldRoleName = roleForUpdate.getName();

        roleForUpdate.setName(expectedName);
        roleRepository.update(roleForUpdate);

        Role role = roleRepository.findById(3L).get();

        Assertions.assertNotEquals(expectedName, oldRoleName);
        Assertions.assertEquals(expectedName, role.getName());
    }

    @DisplayName("Delete by ID")
    @Test
    void deleteById() {
        Boolean expectedValue = true;
        int expectedSize = roleRepository.findAll().size();

        Role tempRole = new Role(null, "Role for delete.");
        tempRole = roleRepository.save(tempRole);

        boolean resultDelete = roleRepository.deleteById(tempRole.getId());
        int roleListAfterSize = roleRepository.findAll().size();

        Assertions.assertEquals(expectedValue, resultDelete);
        Assertions.assertEquals(expectedSize, roleListAfterSize);
    }


    @DisplayName("Find by ID")
    @ParameterizedTest
    @CsvSource(value = {
            "1; true",
            "4; true",
            "100; false"
    }, delimiter = ';')
    void findById(Long expectedId, Boolean expectedValue) {
        Optional<Role> role = roleRepository.findById(expectedId);
        Assertions.assertEquals(expectedValue, role.isPresent());
        if (role.isPresent()) {
            Assertions.assertEquals(expectedId, role.get().getId());
        }
    }

    @Test
    void findAll() {
        int expectedSize = 5;
        int resultSize = roleRepository.findAll().size();

        Assertions.assertEquals(expectedSize, resultSize);
    }
}
