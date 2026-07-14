import { BoxSpg, iTxt, SecurityGuard, useRedirect } from "@duosoftbg/bpo-components";
import React, { Fragment } from "react";
import i18n from "i18next";
import styled from "styled-components";
import AdminConsoleSectionItem from "./AdminConsoleSectionItem";
import { doesExistUrl } from "../../../utils/sidebar";

const SectionTxt = styled.a<{ haveUrl: boolean }>`
  font-size: 1.8rem;
  font-weight: bold;
  color: #333;
  display: flex;
  align-items: center;
  position: relative;
  padding-left: 20px; /* Space for the border */
  margin: 20px 0;

  &::before {
    content: "";
    position: absolute;
    left: 0;
    top: 0;
    bottom: 0;
    width: 10px;
    background-color: rgb(70, 121, 178);
  }

  ${(props) => props.haveUrl && ":hover {  text-decoration: underline;  cursor: pointer;} "}
`;

const AdminConsoleSection = ({ section }) => {
  const accessRoles = section.accessRoles;
  if (accessRoles && accessRoles.length > 0) {
    return (
      <SecurityGuard checkForRoles={accessRoles} roleOperator={"or"}>
        <AdminConsoleSectionContent section={section} />
      </SecurityGuard>
    );
  }

  return <AdminConsoleSectionContent section={section} />;
};

const AdminConsoleSectionContent = ({ section }) => {
  const { redirect } = useRedirect();
  const title = iTxt(i18n.language, section.title, section.titleEn, true);
  const isLink = doesExistUrl(section);

  return (
    <>
      {title && (
        <BoxSpg>
          {isLink ? (
            <SectionTxt haveUrl={isLink} onClick={() => redirect(section.url)}>
              {title}
            </SectionTxt>
          ) : (
            <SectionTxt haveUrl={isLink}>{title}</SectionTxt>
          )}
        </BoxSpg>
      )}
      {section?.children.map((item) => (
        <Fragment key={"section-main-page" + item.id}>
          <AdminConsoleSectionItem item={item} />
        </Fragment>
      ))}
    </>
  );
};

export default AdminConsoleSection;
