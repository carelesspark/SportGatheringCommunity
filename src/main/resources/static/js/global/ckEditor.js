CKEDITOR.ClassicEditor.create(document.getElementById("ckEditor"), {
  toolbar: {
    items: [
      //'exportPDF', 'exportWord', '|',
      "findAndReplace",
      "selectAll",
      "|",
      "heading",
      "|",
      "bold",
      "italic",
      "strikethrough",
      "underline",
      "code",
      "subscript",
      "superscript",
      "removeFormat",
      "|",
      "bulletedList",
      "numberedList",
      "todoList",
      "|",
      "outdent",
      "indent",
      "|",
      "undo",
      "redo",
      "|",
      "fontSize",
      "fontColor",
      "fontBackgroundColor",
      "highlight",
      "|",
      "specialCharacters",
      "horizontalLine",
      "pageBreak",
      "|",
      "sourceEditing",
    ],
    shouldNotGroupWhenFull: true,
  },
  list: {
    properties: {
      styles: true,
      startIndex: true,
      reversed: true,
    },
  },
  heading: {
    options: [
      {
        model: "paragraph",
        title: "Paragraph",
        class: "ck-heading_paragraph",
      },
      {
        model: "heading1",
        view: "h1",
        title: "Heading 1",
        class: "ck-heading_heading1",
      },
      {
        model: "heading2",
        view: "h2",
        title: "Heading 2",
        class: "ck-heading_heading2",
      },
      {
        model: "heading3",
        view: "h3",
        title: "Heading 3",
        class: "ck-heading_heading3",
      },
      {
        model: "heading4",
        view: "h4",
        title: "Heading 4",
        class: "ck-heading_heading4",
      },
      {
        model: "heading5",
        view: "h5",
        title: "Heading 5",
        class: "ck-heading_heading5",
      },
      {
        model: "heading6",
        view: "h6",
        title: "Heading 6",
        class: "ck-heading_heading6",
      },
    ],
  },
  placeholder: "클럽 소개를 입력하세요",
  fontSize: {
    options: [10, 12, 14, "default", 18, 20, 22],
    supportAllValues: true,
  },
  htmlSupport: {
    allow: [
      {
        name: /.*/,
        attributes: true,
        classes: true,
        styles: true,
      },
    ],
  },
  htmlEmbed: {
    showPreviews: true,
  },
  removePlugins: [
    "ExportPdf",
    "ExportWord",
    "CKBox",
    "CKFinder",
    "EasyImage",
    "RealTimeCollaborativeComments",
    "RealTimeCollaborativeTrackChanges",
    "RealTimeCollaborativeRevisionHistory",
    "PresenceList",
    "Comments",
    "TrackChanges",
    "TrackChangesData",
    "RevisionHistory",
    "Pagination",
    "WProofreader",
    "MathType",
  ],
});
