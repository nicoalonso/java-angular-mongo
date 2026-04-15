package com.lacedorium.library.domain.identity.list;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public enum ValueKind {
    STRING,
    BOOLEAN,
    INTEGER,
    FLOAT,
    DATE;

    public <T, U> U parse(T value) {
        return (U) switch (this) {
            case STRING -> parseString(value);
            case BOOLEAN -> parseBool(value);
            case INTEGER -> parseInteger(value);
            case FLOAT -> parseFloat(value);
            case DATE -> parseDate(value);
        };
    }

    public static <T> String parseString(T value) {
        if (value == null) {
            return "";
        }

        return switch (value) {
            case String s -> s;
            case Number n -> n.toString();
            case Boolean b -> b.toString();
            case LocalDate ld -> ld.toString();
            case LocalDateTime ldt -> ldt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            default -> "";
        };
    }

    public static <T> Boolean parseBool(T value) {
        return switch (value) {
            case Boolean b -> b;
            case Number n -> n.intValue() != 0;
            case String s -> {
                String str = s.toLowerCase();
                yield str.equals("true")
                        || str.equals("1")
                        || str.equals("yes")
                        || str.equals("y")
                        || str.equals("on")
                        || str.equals("enabled")
                        || str.equals("active");
            }
            case null, default -> false;
        };
    }

    public static <T> Integer parseInteger(T value) {
        try {
            return switch (value) {
                case null -> 0;
                case Boolean b -> b ? 1 : 0;
                case Integer i -> i;
                case Number n -> n.intValue();
                default -> Integer.valueOf(value.toString());
            };

        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static <T> Float parseFloat(T value) {
        try {
            return switch (value) {
                case null -> 0.0f;
                case Boolean b -> b ? 1f : 0f;
                case Float f -> f;
                case Number n -> n.floatValue();
                default -> Float.valueOf(value.toString());
            };

        } catch (NumberFormatException e) {
            return 0.0f;
        }
    }

    public static <T> @Nullable LocalDateTime parseDate(T value) {
        return switch (value) {
            case LocalDateTime ldt -> ldt;
            case LocalDate ld -> ld.atStartOfDay();
            case String s -> parseDateByFormats(s);
            case null, default -> null;
        };
    }

    private static LocalDateTime parseDateByFormats(String value) {
        LocalDateTime date = null;
        try {
            date = LocalDate.parse(value, DateTimeFormatter.ISO_DATE).atStartOfDay();
        } catch (DateTimeParseException e) {
            // try next formats
        }
        if (null != date) {
            return date;
        }

        DateTimeFormatter[] formats = {
                DateTimeFormatter.ISO_DATE_TIME,
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"),
        };

        for (DateTimeFormatter format : formats) {
            try {
                date = LocalDateTime.parse(value, format);
                break;
            } catch (DateTimeParseException e) {
                // try next format
            }
        }

        return date;
    }

    public static @NonNull Boolean isShortDate(@NonNull String value) {
        try {
            LocalDate.parse(value, DateTimeFormatter.ISO_DATE);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static @NonNull List<String> parseList(@NonNull String value) {
        List<String> items = List.of(value.split("[,; ]"));
        return items.stream()
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .toList();
    }
}
