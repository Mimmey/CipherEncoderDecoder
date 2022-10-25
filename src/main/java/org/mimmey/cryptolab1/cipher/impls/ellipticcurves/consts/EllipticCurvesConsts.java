package org.mimmey.cryptolab1.cipher.impls.ellipticcurves.consts;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.mimmey.cryptolab1.cipher.impls.ellipticcurves.util.Point;

import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EllipticCurvesConsts {
    private final static Map<Character, Point> ALPHABET = Map.of(
            ' ', Point.of(33, 355),
            '!', Point.of(22, 396),
            '\"', Point.of(34, 74),
            '#', Point.of(34, 677),
            '$', Point.of(36, 87),
            '%', Point.of(36, 664),
            '&', Point.of(39, 171),
            '\'', Point.of(39, 580),
            '(', Point.of(43, 224),
            ')', Point.of(43, 527),
            '*', Point.of(44, 366),
            '+', Point.of(44, 385),
            ',', Point.of(45, 31),
            '-', Point.of(45, 720),
            '.', Point.of(47, 349),
            '/', Point.of(47, 402),
            '0', Point.of(48, 49),
            '1', Point.of(48, 702),
            '2', Point.of(49, 183),
            '3', Point.of(49, 568),
            '4', Point.of(53, 277),
            '5', Point.of(53, 474),
            '6', Point.of(56, 332),
            '7', Point.of(56, 419),
            '8', Point.of(58, 139),
            '9', Point.of(58, 612),
            ':', Point.of(59, 365),
            ';', Point.of(59, 386),
            '<', Point.of(61, 129),
            '=', Point.of(61, 622),
            '>', Point.of(62, 372),
            '?', Point.of(62, 379),
            '@', Point.of(66, 199),
            'A', Point.of(66, 552),
            'B', Point.of(67, 84),
            'C', Point.of(67, 667),
            'D', Point.of(69, 241),
            'E', Point.of(69, 510),
            'F', Point.of(70, 195),
            'G', Point.of(70, 556),
            'H', Point.of(72, 254),
            'I', Point.of(72, 497),
            'J', Point.of(73, 72),
            'K', Point.of(73, 679),
            'L', Point.of(74, 170),
            'M', Point.of(74, 581),
            'N', Point.of(75, 318),
            'O', Point.of(75, 433),
            'P', Point.of(78, 271),
            'Q', Point.of(78, 480),
            'R', Point.of(79, 111),
            'S', Point.of(79, 640),
            'T', Point.of(80, 318),
            'U', Point.of(80, 433),
            'V', Point.of(82, 270),
            'W', Point.of(82, 481),
            'X', Point.of(83, 373),
            'Y', Point.of(83, 378),
            'Z', Point.of(85, 35),
            '[', Point.of(85, 716),
            '\\', Point.of(86, 25),
            ']', Point.of(86, 726),
            '^', Point.of(90, 21),
            '_', Point.of(90, 730),
            '`', Point.of(93, 267),
            'a', Point.of(93, 484),
            'b', Point.of(98, 338),
            'c', Point.of(98, 413),
            'd', Point.of(99, 295),
            'e', Point.of(99, 456),
            'f', Point.of(100, 364),
            'g', Point.of(100, 387),
            'h', Point.of(102, 267),
            'i', Point.of(102, 484),
            'j', Point.of(105, 369),
            'k', Point.of(105, 382),
            'l', Point.of(106, 24),
            'm', Point.of(106, 727),
            'n', Point.of(108, 247),
            'o', Point.of(108, 504),
            'p', Point.of(109, 200),
            'q', Point.of(109, 551),
            'r', Point.of(110, 129),
            's', Point.of(110, 622),
            't', Point.of(114, 144),
            'u', Point.of(114, 607),
            'v', Point.of(115, 242),
            'w', Point.of(115, 509),
            'x', Point.of(116, 92),
            'y', Point.of(116, 659),
            'z', Point.of(120, 147),
            '{', Point.of(120, 604),
            '|', Point.of(125, 292),
            '}', Point.of(125, 459),
            '~', Point.of(126, 33),
            'А', Point.of(189, 297),
            'Б', Point.of(189, 454),
            'В', Point.of(192, 32),
            'Г', Point.of(192, 719),
            'Д', Point.of(194, 205),
            'Е', Point.of(194, 546),
            'Ж', Point.of(197, 145),
            'З', Point.of(197, 606),
            'И', Point.of(198, 224),
            'Й', Point.of(198, 527),
            'К', Point.of(200, 30),
            'Л', Point.of(200, 721),
            'М', Point.of(203, 324),
            'Н', Point.of(203, 427),
            'О', Point.of(205, 372),
            'П', Point.of(205, 379),
            'Р', Point.of(206, 106),
            'С', Point.of(206, 645),
            'Т', Point.of(209, 82),
            'У', Point.of(209, 669),
            'Ф', Point.of(210, 31),
            'Х', Point.of(210, 720),
            'Ц', Point.of(215, 247),
            'Ч', Point.of(215, 504),
            'Ш', Point.of(218, 150),
            'Щ', Point.of(218, 601),
            'Ъ', Point.of(221, 138),
            'Ы', Point.of(221, 613),
            'Ь', Point.of(226, 9),
            'Э', Point.of(226, 742),
            'Ю', Point.of(227, 299),
            'Я', Point.of(227, 452),
            'а', Point.of(228, 271),
            'б', Point.of(228, 480),
            'в', Point.of(229, 151),
            'г', Point.of(229, 600),
            'д', Point.of(234, 164),
            'е', Point.of(234, 587),
            'ж', Point.of(235, 19),
            'з', Point.of(235, 732),
            'и', Point.of(236, 39),
            'й', Point.of(236, 712),
            'к', Point.of(237, 297),
            'л', Point.of(237, 454),
            'м', Point.of(238, 175),
            'н', Point.of(238, 576),
            'о', Point.of(240, 309),
            'п', Point.of(240, 442),
            'р', Point.of(243, 87),
            'с', Point.of(243, 664),
            'т', Point.of(247, 266),
            'у', Point.of(247, 485),
            'ф', Point.of(249, 183),
            'х', Point.of(249, 568),
            'ц', Point.of(250, 14),
            'ч', Point.of(250, 737),
            'ш', Point.of(251, 245),
            'щ', Point.of(251, 506),
            'ъ', Point.of(253, 211),
            'ы', Point.of(253, 540),
            'ь', Point.of(256, 121),
            'э', Point.of(256, 630),
            'ю', Point.of(257, 293),
            'я', Point.of(257, 458));
}