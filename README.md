# Spring PetClinic: IntelliJ IDEA in VS Code demo

This is the code from the Rock the JVM video on **[Java and Kotlin by IntelliJ IDEA](https://marketplace.visualstudio.com/items?itemName=JetBrains.intellij-server)**, JetBrains' extension that runs IntelliJ IDEA's Java and Kotlin engine inside VS Code, Cursor and other VS Code-based editors.

The video was sponsored by JetBrains.

📺 Video: _link coming soon_

It is a fork of [Spring PetClinic](https://github.com/spring-projects/spring-petclinic) with a few changes added for each demo. Each demo's starting point is a tag, so you can jump to any of them and try it yourself.

## Demos

| Tag | What's there |
| --- | --- |
| `demo1-start` | Navigation, call/type hierarchies, Spring wiring  |
| `demo1-end` | made some minor changes |
| `demo2-start` | Kotlin code used from Java, refactors |
| `demo2-end` | After the cross-language rename and the refactorings |
| `demo3-start` | debugging and fixing tests  |
| `demo3-end` / `demo4-start` | The bug fixed, the starting point for the AI agent demo |
| `demo4-reference` | One working solution to the agent task, with tests |

## Try it

You need JDK 17 or newer and VS Code or Cursor with the extension installed. The extension needs an IntelliJ IDEA subscription after a 30-day free trial. Disable other Java extensions while you use it.

Open the folder and pick **Gradle** when the extension asks which build tool to use. From Demo 2 on, Kotlin is set up in the Gradle build only. If you already picked Maven, add `"intellij.buildTool": "gradle"` to `.vscode/settings.json` and run **Reload Workspace**.

To move to another demo and throw away your changes:

```bash
git reset --hard && git clean -fd && git checkout demo2-start
```

## The agent prompt from Demo 4

Paste this into Cursor's agent on `demo4-start`:

> Add a JSON endpoint GET /api/owners/{ownerId}/visits?from=YYYY-MM-DD&to=YYYY-MM-DD with an optional petId parameter. It returns the owner's visits between from and to, both days included. Use Owner.visitsBetween and the existing OwnerRepository. Return 404 for an unknown owner or pet. Add a @WebMvcTest for it in the same style as VisitControllerTests.

Then compare the result with `demo4-reference`.

## Credits

Spring PetClinic is by the Spring team and contributors, under the [Apache License 2.0](LICENSE.txt). The original README is kept as [`README-petclinic.md`](README-petclinic.md).