CREATE TABLE IF NOT EXISTS prices (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            source_language TEXT NOT NULL,
            target_language TEXT NOT NULL,
            document_type TEXT NOT NULL,
            price_per_page REAL NOT NULL,

    UNIQUE (
            source_language,
            target_language,
            document_type
            )
);

INSERT INTO prices
(source_language, target_language, document_type, price_per_page)
VALUES

-- Английский
('EN', 'RU', 'PERSONAL', 23),
('EN', 'RU', 'OTHER', 24),
('RU', 'EN', 'PERSONAL', 24),
('RU', 'EN', 'OTHER', 25),

-- Белорусский
('BE', 'RU', 'PERSONAL', 23),
('BE', 'RU', 'OTHER', 24),
('RU', 'BE', 'PERSONAL', 24),
('RU', 'BE', 'OTHER', 25),

-- Немецкий
('DE', 'RU', 'PERSONAL', 23),
('DE', 'RU', 'OTHER', 24),
('RU', 'DE', 'PERSONAL', 24),
('RU', 'DE', 'OTHER', 25),

-- Французский
('FR', 'RU', 'PERSONAL', 33),
('FR', 'RU', 'OTHER', 33),
('RU', 'FR', 'PERSONAL', 35),
('RU', 'FR', 'OTHER', 35),

-- Украинский
('UK', 'RU', 'PERSONAL', 33),
('UK', 'RU', 'OTHER', 33),
('RU', 'UK', 'PERSONAL', 35),
('RU', 'UK', 'OTHER', 35),

-- Литовский
('LT', 'RU', 'PERSONAL', 34),
('LT', 'RU', 'OTHER', 34),
('RU', 'LT', 'PERSONAL', 35),
('RU', 'LT', 'OTHER', 35),

-- Польский
('PL', 'RU', 'PERSONAL', 34),
('PL', 'RU', 'OTHER', 34),
('RU', 'PL', 'PERSONAL', 35),
('RU', 'PL', 'OTHER', 35),

-- Латышский
('LV', 'RU', 'PERSONAL', 36),
('LV', 'RU', 'OTHER', 36),
('RU', 'LV', 'PERSONAL', 40),
('RU', 'LV', 'OTHER', 40),

-- Итальянский
('IT', 'RU', 'PERSONAL', 36),
('IT', 'RU', 'OTHER', 36),
('RU', 'IT', 'PERSONAL', 40),
('RU', 'IT', 'OTHER', 40),

-- Сербский
('SR', 'RU', 'PERSONAL', 48),
('SR', 'RU', 'OTHER', 48),
('RU', 'SR', 'PERSONAL', 50),
('RU', 'SR', 'OTHER', 50),

-- Хорватский
('HR', 'RU', 'PERSONAL', 48),
('HR', 'RU', 'OTHER', 48),
('RU', 'HR', 'PERSONAL', 50),
('RU', 'HR', 'OTHER', 50),

-- Словацкий
('SK', 'RU', 'PERSONAL', 55),
('SK', 'RU', 'OTHER', 55),
('RU', 'SK', 'PERSONAL', 57),
('RU', 'SK', 'OTHER', 57),

-- Молдавский
('MO', 'RU', 'PERSONAL', 55),
('MO', 'RU', 'OTHER', 55),
('RU', 'MO', 'PERSONAL', 57),
('RU', 'MO', 'OTHER', 57),

-- Румынский
('RO', 'RU', 'PERSONAL', 55),
('RO', 'RU', 'OTHER', 55),
('RU', 'RO', 'PERSONAL', 57),
('RU', 'RO', 'OTHER', 57),

-- Венгерский
('HU', 'RU', 'PERSONAL', 55),
('HU', 'RU', 'OTHER', 55),
('RU', 'HU', 'PERSONAL', 57),
('RU', 'HU', 'OTHER', 57),

-- Казахский
('KK', 'RU', 'PERSONAL', 60),
('KK', 'RU', 'OTHER', 60),
('RU', 'KK', 'PERSONAL', 65),
('RU', 'KK', 'OTHER', 65),

-- Грузинский
('KA', 'RU', 'PERSONAL', 60),
('KA', 'RU', 'OTHER', 70),
('RU', 'KA', 'PERSONAL', 60),
('RU', 'KA', 'OTHER', 70),

-- Китайский
('ZH', 'RU', 'PERSONAL', 40),
('ZH', 'RU', 'OTHER', 40),
('RU', 'ZH', 'PERSONAL', 42),
('RU', 'ZH', 'OTHER', 42),

-- Арабский
('AR', 'RU', 'PERSONAL', 48),
('AR', 'RU', 'OTHER', 48),
('RU', 'AR', 'PERSONAL', 52),
('RU', 'AR', 'OTHER', 52),

-- Болгарский
('BG', 'RU', 'PERSONAL', 60),
('BG', 'RU', 'OTHER', 60),
('RU', 'BG', 'PERSONAL', 65),
('RU', 'BG', 'OTHER', 65),

-- Турецкий
('TR', 'RU', 'PERSONAL', 42),
('TR', 'RU', 'OTHER', 42),
('RU', 'TR', 'PERSONAL', 45),
('RU', 'TR', 'OTHER', 45),

-- Испанский
('ES', 'RU', 'PERSONAL', 35),
('ES', 'RU', 'OTHER', 35),
('RU', 'ES', 'PERSONAL', 37),
('RU', 'ES', 'OTHER', 37),

-- Португальский
('PT', 'RU', 'PERSONAL', 55),
('PT', 'RU', 'OTHER', 55),
('RU', 'PT', 'PERSONAL', 57),
('RU', 'PT', 'OTHER', 57),

-- Чешский
('CS', 'RU', 'PERSONAL', 30),
('CS', 'RU', 'OTHER', 30),
('RU', 'CS', 'PERSONAL', 32),
('RU', 'CS', 'OTHER', 32),

-- Узбекский
('UZ', 'RU', 'PERSONAL', 70),
('UZ', 'RU', 'OTHER', 70),
('RU', 'UZ', 'PERSONAL', 75),
('RU', 'UZ', 'OTHER', 75),

-- Армянский
('HY', 'RU', 'PERSONAL', 65),
('HY', 'RU', 'OTHER', 65),
('RU', 'HY', 'PERSONAL', 70),
('RU', 'HY', 'OTHER', 70),

-- Азербайджанский
('AZ', 'RU', 'PERSONAL', 60),
('AZ', 'RU', 'OTHER', 60),
('RU', 'AZ', 'PERSONAL', 65),
('RU', 'AZ', 'OTHER', 65),

-- Нидерландский
('NL', 'RU', 'PERSONAL', 60),
('NL', 'RU', 'OTHER', 60),
('RU', 'NL', 'PERSONAL', 65),
('RU', 'NL', 'OTHER', 65),

-- Иврит
('HE', 'RU', 'PERSONAL', 70),
('HE', 'RU', 'OTHER', 70),
('RU', 'HE', 'PERSONAL', 80),
('RU', 'HE', 'OTHER', 80),

-- Эстонский
('ET', 'RU', 'PERSONAL', 70),
('ET', 'RU', 'OTHER', 70),
('RU', 'ET', 'PERSONAL', 70),
('RU', 'ET', 'OTHER', 70),

-- Греческий
('EL', 'RU', 'PERSONAL', 70),
('EL', 'RU', 'OTHER', 70),
('RU', 'EL', 'PERSONAL', 70),
('RU', 'EL', 'OTHER', 70),

-- Туркменский
('TK', 'RU', 'PERSONAL', 60),
('TK', 'RU', 'OTHER', 60),
('RU', 'TK', 'PERSONAL', 65),
('RU', 'TK', 'OTHER', 65),

-- Персидский
('FA', 'RU', 'PERSONAL', 70),
('FA', 'RU', 'OTHER', 70),
('RU', 'FA', 'PERSONAL', 75),
('RU', 'FA', 'OTHER', 75),

-- Японский
('JA', 'RU', 'PERSONAL', 70),
('JA', 'RU', 'OTHER', 70),
('RU', 'JA', 'PERSONAL', 75),
('RU', 'JA', 'OTHER', 75),

-- Вьетнамский
('VI', 'RU', 'PERSONAL', 70),
('VI', 'RU', 'OTHER', 70),
('RU', 'VI', 'PERSONAL', 75),
('RU', 'VI', 'OTHER', 75),

-- Норвежский
('NO', 'RU', 'PERSONAL', 70),
('NO', 'RU', 'OTHER', 70),
('RU', 'NO', 'PERSONAL', 75),
('RU', 'NO', 'OTHER', 75),

-- Шведский
('SV', 'RU', 'PERSONAL', 70),
('SV', 'RU', 'OTHER', 70),
('RU', 'SV', 'PERSONAL', 75),
('RU', 'SV', 'OTHER', 75)

    ON CONFLICT (
    source_language,
    target_language,
    document_type
)
DO UPDATE SET
    price_per_page = excluded.price_per_page;