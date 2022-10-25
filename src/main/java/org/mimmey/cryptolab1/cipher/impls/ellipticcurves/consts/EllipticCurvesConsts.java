package org.mimmey.cryptolab1.cipher.impls.ellipticcurves.consts;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.mimmey.cryptolab1.cipher.impls.ellipticcurves.util.Point;

import java.util.AbstractMap;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EllipticCurvesConsts {
    private final static Map<Character, Point> ALPHABET = Map.ofEntries(
            new AbstractMap.SimpleImmutableEntry<>(' ', Point.of(33, 355)),
            new AbstractMap.SimpleImmutableEntry<>('!', Point.of(22, 396)),
            new AbstractMap.SimpleImmutableEntry<>('\"', Point.of(34, 74)),
            new AbstractMap.SimpleImmutableEntry<>('#', Point.of(34, 677)),
            new AbstractMap.SimpleImmutableEntry<>('$', Point.of(36, 87)),
            new AbstractMap.SimpleImmutableEntry<>('%', Point.of(36, 664)),
            new AbstractMap.SimpleImmutableEntry<>('&', Point.of(39, 171)),
            new AbstractMap.SimpleImmutableEntry<>('\'', Point.of(39, 580)),
            new AbstractMap.SimpleImmutableEntry<>('(', Point.of(43, 224)),
            new AbstractMap.SimpleImmutableEntry<>(')', Point.of(43, 527)),
            new AbstractMap.SimpleImmutableEntry<>('*', Point.of(44, 366)),
            new AbstractMap.SimpleImmutableEntry<>('+', Point.of(44, 385)),
            new AbstractMap.SimpleImmutableEntry<>(',', Point.of(45, 31)),
            new AbstractMap.SimpleImmutableEntry<>('-', Point.of(45, 720)),
            new AbstractMap.SimpleImmutableEntry<>('.', Point.of(47, 349)),
            new AbstractMap.SimpleImmutableEntry<>('/', Point.of(47, 402)),
            new AbstractMap.SimpleImmutableEntry<>('0', Point.of(48, 49)),
            new AbstractMap.SimpleImmutableEntry<>('1', Point.of(48, 702)),
            new AbstractMap.SimpleImmutableEntry<>('2', Point.of(49, 183)),
            new AbstractMap.SimpleImmutableEntry<>('3', Point.of(49, 568)),
            new AbstractMap.SimpleImmutableEntry<>('4', Point.of(53, 277)),
            new AbstractMap.SimpleImmutableEntry<>('5', Point.of(53, 474)),
            new AbstractMap.SimpleImmutableEntry<>('6', Point.of(56, 332)),
            new AbstractMap.SimpleImmutableEntry<>('7', Point.of(56, 419)),
            new AbstractMap.SimpleImmutableEntry<>('8', Point.of(58, 139)),
            new AbstractMap.SimpleImmutableEntry<>('9', Point.of(58, 612)),
            new AbstractMap.SimpleImmutableEntry<>(':', Point.of(59, 365)),
            new AbstractMap.SimpleImmutableEntry<>(';', Point.of(59, 386)),
            new AbstractMap.SimpleImmutableEntry<>('<', Point.of(61, 129)),
            new AbstractMap.SimpleImmutableEntry<>('=', Point.of(61, 622)),
            new AbstractMap.SimpleImmutableEntry<>('>', Point.of(62, 372)),
            new AbstractMap.SimpleImmutableEntry<>('?', Point.of(62, 379)),
            new AbstractMap.SimpleImmutableEntry<>('@', Point.of(66, 199)),
            new AbstractMap.SimpleImmutableEntry<>('A', Point.of(66, 552)),
            new AbstractMap.SimpleImmutableEntry<>('B', Point.of(67, 84)),
            new AbstractMap.SimpleImmutableEntry<>('C', Point.of(67, 667)),
            new AbstractMap.SimpleImmutableEntry<>('D', Point.of(69, 241)),
            new AbstractMap.SimpleImmutableEntry<>('E', Point.of(69, 510)),
            new AbstractMap.SimpleImmutableEntry<>('F', Point.of(70, 195)),
            new AbstractMap.SimpleImmutableEntry<>('G', Point.of(70, 556)),
            new AbstractMap.SimpleImmutableEntry<>('H', Point.of(72, 254)),
            new AbstractMap.SimpleImmutableEntry<>('I', Point.of(72, 497)),
            new AbstractMap.SimpleImmutableEntry<>('J', Point.of(73, 72)),
            new AbstractMap.SimpleImmutableEntry<>('K', Point.of(73, 679)),
            new AbstractMap.SimpleImmutableEntry<>('L', Point.of(74, 170)),
            new AbstractMap.SimpleImmutableEntry<>('M', Point.of(74, 581)),
            new AbstractMap.SimpleImmutableEntry<>('N', Point.of(75, 318)),
            new AbstractMap.SimpleImmutableEntry<>('O', Point.of(75, 433)),
            new AbstractMap.SimpleImmutableEntry<>('P', Point.of(78, 271)),
            new AbstractMap.SimpleImmutableEntry<>('Q', Point.of(78, 480)),
            new AbstractMap.SimpleImmutableEntry<>('R', Point.of(79, 111)),
            new AbstractMap.SimpleImmutableEntry<>('S', Point.of(79, 640)),
            new AbstractMap.SimpleImmutableEntry<>('T', Point.of(80, 318)),
            new AbstractMap.SimpleImmutableEntry<>('U', Point.of(80, 433)),
            new AbstractMap.SimpleImmutableEntry<>('V', Point.of(82, 270)),
            new AbstractMap.SimpleImmutableEntry<>('W', Point.of(82, 481)),
            new AbstractMap.SimpleImmutableEntry<>('X', Point.of(83, 373)),
            new AbstractMap.SimpleImmutableEntry<>('Y', Point.of(83, 378)),
            new AbstractMap.SimpleImmutableEntry<>('Z', Point.of(85, 35)),
            new AbstractMap.SimpleImmutableEntry<>('[', Point.of(85, 716)),
            new AbstractMap.SimpleImmutableEntry<>('\\', Point.of(86, 25)),
            new AbstractMap.SimpleImmutableEntry<>(']', Point.of(86, 726)),
            new AbstractMap.SimpleImmutableEntry<>('^', Point.of(90, 21)),
            new AbstractMap.SimpleImmutableEntry<>('_', Point.of(90, 730)),
            new AbstractMap.SimpleImmutableEntry<>('`', Point.of(93, 267)),
            new AbstractMap.SimpleImmutableEntry<>('a', Point.of(93, 484)),
            new AbstractMap.SimpleImmutableEntry<>('b', Point.of(98, 338)),
            new AbstractMap.SimpleImmutableEntry<>('c', Point.of(98, 413)),
            new AbstractMap.SimpleImmutableEntry<>('d', Point.of(99, 295)),
            new AbstractMap.SimpleImmutableEntry<>('e', Point.of(99, 456)),
            new AbstractMap.SimpleImmutableEntry<>('f', Point.of(100, 364)),
            new AbstractMap.SimpleImmutableEntry<>('g', Point.of(100, 387)),
            new AbstractMap.SimpleImmutableEntry<>('h', Point.of(102, 267)),
            new AbstractMap.SimpleImmutableEntry<>('i', Point.of(102, 484)),
            new AbstractMap.SimpleImmutableEntry<>('j', Point.of(105, 369)),
            new AbstractMap.SimpleImmutableEntry<>('k', Point.of(105, 382)),
            new AbstractMap.SimpleImmutableEntry<>('l', Point.of(106, 24)),
            new AbstractMap.SimpleImmutableEntry<>('m', Point.of(106, 727)),
            new AbstractMap.SimpleImmutableEntry<>('n', Point.of(108, 247)),
            new AbstractMap.SimpleImmutableEntry<>('o', Point.of(108, 504)),
            new AbstractMap.SimpleImmutableEntry<>('p', Point.of(109, 200)),
            new AbstractMap.SimpleImmutableEntry<>('q', Point.of(109, 551)),
            new AbstractMap.SimpleImmutableEntry<>('r', Point.of(110, 129)),
            new AbstractMap.SimpleImmutableEntry<>('s', Point.of(110, 622)),
            new AbstractMap.SimpleImmutableEntry<>('t', Point.of(114, 144)),
            new AbstractMap.SimpleImmutableEntry<>('u', Point.of(114, 607)),
            new AbstractMap.SimpleImmutableEntry<>('v', Point.of(115, 242)),
            new AbstractMap.SimpleImmutableEntry<>('w', Point.of(115, 509)),
            new AbstractMap.SimpleImmutableEntry<>('x', Point.of(116, 92)),
            new AbstractMap.SimpleImmutableEntry<>('y', Point.of(116, 659)),
            new AbstractMap.SimpleImmutableEntry<>('z', Point.of(120, 147)),
            new AbstractMap.SimpleImmutableEntry<>('{', Point.of(120, 604)),
            new AbstractMap.SimpleImmutableEntry<>('|', Point.of(125, 292)),
            new AbstractMap.SimpleImmutableEntry<>('}', Point.of(125, 459)),
            new AbstractMap.SimpleImmutableEntry<>('~', Point.of(126, 33)),
            new AbstractMap.SimpleImmutableEntry<>('А', Point.of(189, 297)),
            new AbstractMap.SimpleImmutableEntry<>('Б', Point.of(189, 454)),
            new AbstractMap.SimpleImmutableEntry<>('В', Point.of(192, 32)),
            new AbstractMap.SimpleImmutableEntry<>('Г', Point.of(192, 719)),
            new AbstractMap.SimpleImmutableEntry<>('Д', Point.of(194, 205)),
            new AbstractMap.SimpleImmutableEntry<>('Е', Point.of(194, 546)),
            new AbstractMap.SimpleImmutableEntry<>('Ж', Point.of(197, 145)),
            new AbstractMap.SimpleImmutableEntry<>('З', Point.of(197, 606)),
            new AbstractMap.SimpleImmutableEntry<>('И', Point.of(198, 224)),
            new AbstractMap.SimpleImmutableEntry<>('Й', Point.of(198, 527)),
            new AbstractMap.SimpleImmutableEntry<>('К', Point.of(200, 30)),
            new AbstractMap.SimpleImmutableEntry<>('Л', Point.of(200, 721)),
            new AbstractMap.SimpleImmutableEntry<>('М', Point.of(203, 324)),
            new AbstractMap.SimpleImmutableEntry<>('Н', Point.of(203, 427)),
            new AbstractMap.SimpleImmutableEntry<>('О', Point.of(205, 372)),
            new AbstractMap.SimpleImmutableEntry<>('П', Point.of(205, 379)),
            new AbstractMap.SimpleImmutableEntry<>('Р', Point.of(206, 106)),
            new AbstractMap.SimpleImmutableEntry<>('С', Point.of(206, 645)),
            new AbstractMap.SimpleImmutableEntry<>('Т', Point.of(209, 82)),
            new AbstractMap.SimpleImmutableEntry<>('У', Point.of(209, 669)),
            new AbstractMap.SimpleImmutableEntry<>('Ф', Point.of(210, 31)),
            new AbstractMap.SimpleImmutableEntry<>('Х', Point.of(210, 720)),
            new AbstractMap.SimpleImmutableEntry<>('Ц', Point.of(215, 247)),
            new AbstractMap.SimpleImmutableEntry<>('Ч', Point.of(215, 504)),
            new AbstractMap.SimpleImmutableEntry<>('Ш', Point.of(218, 150)),
            new AbstractMap.SimpleImmutableEntry<>('Щ', Point.of(218, 601)),
            new AbstractMap.SimpleImmutableEntry<>('Ъ', Point.of(221, 138)),
            new AbstractMap.SimpleImmutableEntry<>('Ы', Point.of(221, 613)),
            new AbstractMap.SimpleImmutableEntry<>('Ь', Point.of(226, 9)),
            new AbstractMap.SimpleImmutableEntry<>('Э', Point.of(226, 742)),
            new AbstractMap.SimpleImmutableEntry<>('Ю', Point.of(227, 299)),
            new AbstractMap.SimpleImmutableEntry<>('Я', Point.of(227, 452)),
            new AbstractMap.SimpleImmutableEntry<>('а', Point.of(228, 271)),
            new AbstractMap.SimpleImmutableEntry<>('б', Point.of(228, 480)),
            new AbstractMap.SimpleImmutableEntry<>('в', Point.of(229, 151)),
            new AbstractMap.SimpleImmutableEntry<>('г', Point.of(229, 600)),
            new AbstractMap.SimpleImmutableEntry<>('д', Point.of(234, 164)),
            new AbstractMap.SimpleImmutableEntry<>('е', Point.of(234, 587)),
            new AbstractMap.SimpleImmutableEntry<>('ж', Point.of(235, 19)),
            new AbstractMap.SimpleImmutableEntry<>('з', Point.of(235, 732)),
            new AbstractMap.SimpleImmutableEntry<>('и', Point.of(236, 39)),
            new AbstractMap.SimpleImmutableEntry<>('й', Point.of(236, 712)),
            new AbstractMap.SimpleImmutableEntry<>('к', Point.of(237, 297)),
            new AbstractMap.SimpleImmutableEntry<>('л', Point.of(237, 454)),
            new AbstractMap.SimpleImmutableEntry<>('м', Point.of(238, 175)),
            new AbstractMap.SimpleImmutableEntry<>('н', Point.of(238, 576)),
            new AbstractMap.SimpleImmutableEntry<>('о', Point.of(240, 309)),
            new AbstractMap.SimpleImmutableEntry<>('п', Point.of(240, 442)),
            new AbstractMap.SimpleImmutableEntry<>('р', Point.of(243, 87)),
            new AbstractMap.SimpleImmutableEntry<>('с', Point.of(243, 664)),
            new AbstractMap.SimpleImmutableEntry<>('т', Point.of(247, 266)),
            new AbstractMap.SimpleImmutableEntry<>('у', Point.of(247, 485)),
            new AbstractMap.SimpleImmutableEntry<>('ф', Point.of(249, 183)),
            new AbstractMap.SimpleImmutableEntry<>('х', Point.of(249, 568)),
            new AbstractMap.SimpleImmutableEntry<>('ц', Point.of(250, 14)),
            new AbstractMap.SimpleImmutableEntry<>('ч', Point.of(250, 737)),
            new AbstractMap.SimpleImmutableEntry<>('ш', Point.of(251, 245)),
            new AbstractMap.SimpleImmutableEntry<>('щ', Point.of(251, 506)),
            new AbstractMap.SimpleImmutableEntry<>('ъ', Point.of(253, 211)),
            new AbstractMap.SimpleImmutableEntry<>('ы', Point.of(253, 540)),
            new AbstractMap.SimpleImmutableEntry<>('ь', Point.of(256, 121)),
            new AbstractMap.SimpleImmutableEntry<>('э', Point.of(256, 630)),
            new AbstractMap.SimpleImmutableEntry<>('ю', Point.of(257, 293)),
            new AbstractMap.SimpleImmutableEntry<>('я', Point.of(257, 458))
    );
}
