/******************************************************************************
 * Copyright (c) 2002 - 2006 IBM Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *****************************************************************************/
/*
 * Created on Oct 21, 2005
 */
package com.ibm.wala.cast.java.test;

import java.io.File;

import com.ibm.wala.cast.java.client.JavaSourceAnalysisEngine;
import com.ibm.wala.eclipse.util.EclipseProjectPath;
import com.ibm.wala.ipa.callgraph.AnalysisScope;
import com.ibm.wala.ipa.callgraph.Entrypoint;
import com.ibm.wala.ipa.callgraph.impl.Util;
import com.ibm.wala.ipa.cha.ClassHierarchy;

public class JavaIRTests extends IRTests {
  public JavaIRTests(String name) {
    super(name);
  }

  protected JavaSourceAnalysisEngine getAnalysisEngine(final String[] mainClassDescriptors) {
    return new JavaSourceAnalysisEngine() {
      protected Iterable<Entrypoint> makeDefaultEntrypoints(AnalysisScope scope, ClassHierarchy cha) {
        return Util.makeMainEntrypoints(EclipseProjectPath.SOURCE_REF, cha, mainClassDescriptors);
      }
    };
  }

  protected String singleInputForTest() {
    return getName().substring(4) + ".java";
  }

  protected String singlePkgInputForTest(String pkgName) {
    return pkgName + File.separator + getName().substring(4) + ".java";
  }

  public void testSimple1() {
    SourceMapAssertions sa = new SourceMapAssertions();
    sa.addAssertion("Source#Simple1#doStuff#(I)V", new SourceMapAssertion("prod", 14));
    sa.addAssertion("Source#Simple1#doStuff#(I)V", new SourceMapAssertion("j", 13));
    sa.addAssertion("Source#Simple1#main#([Ljava/lang/String;)V", new SourceMapAssertion("s", 22));
    sa.addAssertion("Source#Simple1#main#([Ljava/lang/String;)V", new SourceMapAssertion("i", 18));
    sa.addAssertion("Source#Simple1#main#([Ljava/lang/String;)V", new SourceMapAssertion("sum", 19));

    runTest(singleTestSrc(), rtJar, simpleTestEntryPoint(), new GraphAssertions(EdgeAssertions.make(
        "Source#Simple1#main#([Ljava/lang/String;)V", "Source#Simple1#doStuff#(I)V"), EdgeAssertions.make(
        "Source#Simple1#instanceMethod1#()V", "Source#Simple1#instanceMethod2#()V")),
    // this needs soure positions to work too
        sa, true);
  }

  public void testTwoClasses() {
    runTest(singleTestSrc(), rtJar, simpleTestEntryPoint(), new GraphAssertions(), null, true);
  }

  public void testInterfaceTest1() {
    runTest(singleTestSrc(), rtJar, simpleTestEntryPoint(), new GraphAssertions(), null, true);
  }

  public void testInheritance1() {
    runTest(singleTestSrc(), rtJar, simpleTestEntryPoint(), new GraphAssertions(), null, true);
  }

  public void testArray1() {
    runTest(singleTestSrc(), rtJar, simpleTestEntryPoint(), new GraphAssertions(), null, true);
  }

  public void testArrayLiteral1() {
    runTest(singleTestSrc(), rtJar, simpleTestEntryPoint(), new GraphAssertions(), null, true);
  }

  public void testArrayLiteral2() {
    runTest(singleTestSrc(), rtJar, simpleTestEntryPoint(), new GraphAssertions(), null, true);
  }

  public void testInheritedField() {
    runTest(singleTestSrc(), rtJar, simpleTestEntryPoint(), new GraphAssertions(EdgeAssertions.make(
        "Source#InheritedField#main#([Ljava/lang/String;)V", "Source#B#foo#()V"), EdgeAssertions.make(
        "Source#InheritedField#main#([Ljava/lang/String;)V", "Source#B#bar#()V")), null, true);
  }

  public void testQualifiedStatic() {
    runTest(singleTestSrc(), rtJar, simpleTestEntryPoint(), new GraphAssertions(), null, true);
  }

  public void testStaticNesting() {
    runTest(singleTestSrc(), rtJar, simpleTestEntryPoint(), new GraphAssertions(), null, true);
  }

  public void testInnerClass() {
    runTest(singleTestSrc(), rtJar, simpleTestEntryPoint(), new GraphAssertions(), null, true);
  }

  public void testLocalClass() {
    runTest(singleTestSrc(), rtJar, simpleTestEntryPoint(), new GraphAssertions(), null, true);
  }

  public void testAnonymousClass() {
    runTest(singleTestSrc(), rtJar, simpleTestEntryPoint(), new GraphAssertions(), null, true);
  }

  public void testWhileTest1() {
    runTest(singleTestSrc(), rtJar, simpleTestEntryPoint(), new GraphAssertions(), null, true);
  }

  public void testSwitch1() {
    runTest(singleTestSrc(), rtJar, simpleTestEntryPoint(), new GraphAssertions(), null, true);
  }

  public void testException1() {
    runTest(singleTestSrc(), rtJar, simpleTestEntryPoint(), new GraphAssertions(), null, true);
  }

  public void testException2() {
    runTest(singleTestSrc(), rtJar, simpleTestEntryPoint(), new GraphAssertions(), null, true);
  }

  public void testFinally1() {
    runTest(singleTestSrc(), rtJar, simpleTestEntryPoint(), new GraphAssertions(), null, true);
  }

  public void testScoping1() {
    runTest(singleTestSrc(), rtJar, simpleTestEntryPoint(), new GraphAssertions(), null, true);
  }

  public void testScoping2() {
    runTest(singleTestSrc(), rtJar, simpleTestEntryPoint(), new GraphAssertions(), null, true);
  }

  public void testNonPrimaryTopLevel() {
    runTest(singlePkgTestSrc("p"), rtJar, simplePkgTestEntryPoint("p"), new GraphAssertions(), null, true);
  }

  public void testMiniaturList() {
    runTest(singleTestSrc(), rtJar, simpleTestEntryPoint(), new GraphAssertions(), null, true);
  }

  public void testMonitor() {
    runTest(singleTestSrc(), rtJar, simpleTestEntryPoint(), new GraphAssertions(), null, true);
  }

  public void testStaticInit() {
    runTest(singleTestSrc(), rtJar, simpleTestEntryPoint(), new GraphAssertions(), null, true);
  }

  public void testThread1() {
    runTest(singleTestSrc(), rtJar, simpleTestEntryPoint(), new GraphAssertions(), null, true);
  }

}
