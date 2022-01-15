# Mosaic

Mosaic is a very simple parser for what I call "Mosaic text". This is an alternative to Markdown but
doesn't support as many types as Markdown for now.

_Note: This project is based on Android's TextStyling user interface sample which you can find
[here](https://github.com/android/user-interface-samples/tree/main/TextStylingKotlin)._

---

Supported types are:
 - `**bold**`
 - `__italic__`
 - `[link](https://example.com)`

These types can also be combined together, for example: `__[**link**](https://example.com)__` is valid and would give: _[**link**](https://example.com)_
