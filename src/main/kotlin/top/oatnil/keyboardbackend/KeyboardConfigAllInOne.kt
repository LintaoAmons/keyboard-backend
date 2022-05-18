package top.oatnil.keyboardbackend

import org.springframework.data.mongodb.core.mapping.MongoId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/keyboard-config")
class KeyboardConfigController(
    val keyboardConfigRepository: KeyboardConfigRepository,
) {
    @PostMapping
    fun save(@RequestBody keyboardConfig: KeyboardConfig): KeyboardConfig =
        keyboardConfigRepository.save(keyboardConfig)

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): KeyboardConfig =
        keyboardConfigRepository.findById(id).orElseThrow { RuntimeException("Didn't find by id [${id}]") }
}

// MongoRepository
interface KeyboardConfigRepository : MongoRepository<KeyboardConfig, Long>

// Model Define
data class KeyboardConfig(
    @MongoId
    val id: Long,
    val scenarios: List<Scenario>,
    val createdBy: String,
    val createdAt: LocalDateTime,
)

data class Scenario(
    val name: String,
    val config: List<KeyMapItem>,
)

data class KeyMapItem(
    val keycode: String,
    val modifiers: List<Modifier>,
    val description: String,
    val achieveBy: String
)

enum class Modifier {
    CMD,
    CTRL,
    SHIFT,
    ALT,
    TAB,
    HYPER
}
