CREATE TABLE IF NOT EXISTS notes (
                                     id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                                     text LONGTEXT NOT NULL,
                                     color VARCHAR(16) NOT NULL DEFAULT '#FFF176',
    x INT NOT NULL DEFAULT 0,
    y INT NOT NULL DEFAULT 0,
    rotation DECIMAL(5,2) NOT NULL DEFAULT 0.00,
    z_index INT NOT NULL DEFAULT 1,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    INDEX idx_notes_z (z_index),
    INDEX idx_notes_created (created_at)            -- 이모지 지원
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

USE mydb;

INSERT INTO notes (text, color, x, y, rotation, z_index)
VALUES
    ('안녕하세요!\n메모를 드래그해서\n이동할 수 있어요.', '#FFF176', 120, 60, -2.50, 1),
    ('✏️ 클릭하면\n텍스트를 편집할 수\n있어요!', '#F8BBD9', 360, 100, 1.80, 2),
    ('🎨 상단 툴바에서\n색상을 선택하고\n새 메모를 추가해보세요!', '#B2DFDB', 600, 50, -1.20, 3);

SELECT * FROM notes;