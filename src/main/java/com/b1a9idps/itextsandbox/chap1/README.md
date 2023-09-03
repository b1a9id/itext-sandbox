# README for Chapter 1

```java
// PDFファイルを書き出すことができるオブジェクト。PdfWriterはドキュメントについては知らない。
PdfWriter writer = new PdfWriter(dest);
// 追加されるコンテンツを管理する
PdfDocument pdf = new PdfDocument(writer);
Document document = new Document(pdf);
document.add(new Paragraph("Hello World!"));
// ドキュメントをクローズし、PDFが作成される。
document.close();
```


## C01E01_HelloWorld
シンプルにテキストをPDF出力

## C01E02_RickAstley
箇条書きのテキストをPDF出力

## C01E03_QuickBrownFox
画像をPDF出力

## C01E04_UnitedStates
テーブルをPDF出力

